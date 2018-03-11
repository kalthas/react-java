package com.aiexpanse.react.intf.websockets.protocol;

import com.aiexpanse.react.view.api.UISession;
import com.aiexpanse.react.view.api.Widget;
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

        switch (protocolMessage.getType()) {
            case CONNECTION_INIT: {
                messageToReturn = new ProtocolMessage(null, MessageType.CONNECTION_ACK, null);
                break;
            }
            case LOAD: {
                Object payload = protocolMessage.getPayload();
                if (payload instanceof String) {
                    String uiPath = (String) payload;
                    try {
                        sendContent(protocolMessage.getId(), uiSession.getWidget(uiPath));
                    } catch (Exception e) {
                        String reasonString = "Error getting widget for ui path [" + uiPath + "]";
                        LOGGER.error(reasonString, e);
                        sendError(protocolMessage.getId(), reasonString, e);
                    }
                } else {
                    sendContent(null, "payload for load is not string");
                }
                break;
            }
        }
        return messageToReturn;
    }

    public void sendContent(String id, Object data) {
        ProtocolMessage message = new ProtocolMessage(id, MessageType.CONTENT, data);
        try {
            sendMessage(message);
        } catch (IOException | EncodeException e) {
            LOGGER.error("Sending content [id={}] failed: ", id, e);
            closeSessionSafe();
        }
    }

    public void sendError() throws IOException, EncodeException {
        sendMessage(new ProtocolMessage(null, MessageType.ERROR, null));
    }

    public void sendError(String id, String reasonString, Exception exception) {
        Map<String, Object> reason = new HashMap<>();
        reason.put("Reason", reasonString);
        reason.put("Exception", exception);
        try {
            sendMessage(new ProtocolMessage(id, MessageType.ERROR, reason));
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
