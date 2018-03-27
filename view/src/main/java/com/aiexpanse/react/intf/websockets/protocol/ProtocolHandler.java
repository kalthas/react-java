package com.aiexpanse.react.intf.websockets.protocol;

import com.aiexpanse.react.intf.websockets.payload.DefaultMessagePayload;
import com.aiexpanse.react.intf.websockets.payload.UpdateMessagePayload;
import com.aiexpanse.react.intf.websockets.payload.UpdatesEventMessagePayload;
import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.api.UISession;
import com.aiexpanse.react.view.api.Widget;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProtocolHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolHandler.class);

    private Session session;

    @Inject
    private UISession uiSession;

    public void init(Session session) {
        this.session = session;
    }

    public ProtocolMessage handle(ProtocolMessage protocolMessage) {
        ProtocolMessage messageToReturn = null;
        Object rawPayload = protocolMessage.getPayload();
        MessageType messageType = protocolMessage.getType();

        if (messageType == MessageType.CONNECTION_INIT ||
                messageType == MessageType.LOAD ||
                messageType == MessageType.EVENT) {
            DefaultMessagePayload payload = null;
            if (messageType == MessageType.LOAD ||
                    messageType == MessageType.EVENT) {
                if (rawPayload == null) {
                    messageToReturn = newError(protocolMessage.getId(), "", "payload is null");
                } else {
                    Map payloadMap = (Map)rawPayload;
                    payload = new DefaultMessagePayload((String)payloadMap.get("uipath"), payloadMap.get("content"));
                    if (Strings.isNullOrEmpty(payload.getUIPath())) {
                        messageToReturn = newError(protocolMessage.getId(), payload.getUIPath(), "payload.uipath is null or empty");
                    }
                }
            }
            if (messageToReturn != null) {
                return messageToReturn;
            }

            switch (messageType) {
                case CONNECTION_INIT: {
                    messageToReturn = new ProtocolMessage(null, MessageType.CONNECTION_ACK, null);
                    break;
                }
                case LOAD: {
                    String uiPath = payload.getUIPath();
                    try {
                        messageToReturn = newContent(protocolMessage.getId(), uiPath, uiSession.loadWidget(uiPath));
                    } catch (Exception e) {
                        String reasonString = "Error getting widget for uipath [" + uiPath + "]: " + e.getMessage();
                        LOGGER.error(reasonString, e);
                        messageToReturn = newError(protocolMessage.getId(), uiPath, reasonString);
                    }
                    break;
                }
                case EVENT: {
                    messageToReturn = handleEvent(protocolMessage, payload);
                }
            }
        }
        else if (messageType == MessageType.SYNC_EVENT) {
            UpdatesEventMessagePayload payload = null;
            try {
                payload = new UpdatesEventMessagePayload((Map)rawPayload);
            } catch (Exception e) {
                LOGGER.error("Failed to construct UpdatesEventMessagePayload from raw {} ", rawPayload);
                messageToReturn = newError(protocolMessage.getId(), "", "Failed to construct UpdatesEventMessagePayload from raw");
            }
            if (payload != null) {
                switch (messageType) {
                    case SYNC_EVENT:
                        for (UpdateMessagePayload updatePayload : payload.getUpdateEvents()) {
                            handleUpdate(updatePayload);
                        }
                        messageToReturn = handleEvent(protocolMessage, payload.getEvent());
                        break;
                }
            }
        }


        return messageToReturn;
    }

    private void handleUpdate(UpdateMessagePayload updatePayload) {
        Widget widget = uiSession.getWidget(updatePayload.getUiPath());
        if (widget != null) {
            widget.mutateProp(updatePayload.getPropName(), updatePayload.getValue());
        }
    }

    private ProtocolMessage handleEvent(ProtocolMessage protocolMessage, DefaultMessagePayload payload) {
        String uiPath = payload.getUIPath();
        EventType type = null;
        String eventPath = null;
        if (payload.getContent() == null) {
            return newError(protocolMessage.getId(), uiPath, "content is null");
        } else {
            try {
                type = EventType.valueOf((String) (((Map) payload.getContent()).get("type")));
                eventPath = uiPath + "/" + type.name();
            } catch (Exception e) {
                return newError(protocolMessage.getId(), uiPath, "Cannot get event type in payload: " + e.getMessage());
            }
        }
        Event event = uiSession.getEventByPath(eventPath);
        try {
            event.handle();
        } catch (Exception e) {
            String reasonString = "Error handling " + type +  " for uipath [" + uiPath + "]: " + e.getMessage();
            LOGGER.error(reasonString, e);
            return newError(protocolMessage.getId(), uiPath, reasonString);
        }
        return null;
    }

    private ProtocolMessage newContent(String id, String uiPath, Object data) {
        return new ProtocolMessage(id, MessageType.CONTENT, new DefaultMessagePayload(uiPath, data));
    }

    public void sendContent(String id, String uiPath, Object data) {
        ProtocolMessage message = newContent(id, uiPath, data);
        try {
            sendMessage(message);
        } catch (IOException | EncodeException e) {
            LOGGER.error("Sending content [id={}] failed: ", id, e);
            closeSessionSafe();
        }
    }

    private ProtocolMessage newError(String id, String uiPath, String errorMessage) {
        return new ProtocolMessage(id, MessageType.ERROR, new DefaultMessagePayload(uiPath, errorMessage));
    }

    public void sendError() throws IOException, EncodeException {
        sendMessage(new ProtocolMessage(null, MessageType.ERROR, null));
    }

    public void sendError(String id, String reasonString, Exception exception) {
        Map<String, Object> reason = new HashMap<>();
        reason.put("Reason", reasonString);
        reason.put("Exception", exception);
        try {
            sendMessage(new ProtocolMessage(id, MessageType.ERROR, new DefaultMessagePayload("", reason)));
        } catch (IOException | EncodeException e) {
            LOGGER.error("Sending error [id={}] failed: ", id, e);
            closeSessionSafe();
        }
    }

    public void sessionCleanup() {
        // TBD: clean up
    }

    public void closeSession(CloseReason.CloseCode code) throws IOException {
        sessionCleanup();
        session.close(new CloseReason(code, ""));
    }

    private void closeSessionSafe(CloseReason.CloseCode code) {
        try {
            closeSession(code);
        } catch (IOException e) {
            LOGGER.error("Failed to close web socket session", e);
        }
    }

    private void closeSessionSafe() {
        closeSessionSafe(CloseReason.CloseCodes.NORMAL_CLOSURE);
    }

    private void sendMessage(ProtocolMessage protocolMessage) throws IOException, EncodeException {
        if (!session.isOpen() && protocolMessage.getType() == MessageType.EVENT) {
            /*
             * If session is already closed and the purpose is to sent an error message, then there's no point
             * in doing so. hence cancelled safely
             */
            return;
        }
        session.getBasicRemote().sendObject(protocolMessage);
    }

}
