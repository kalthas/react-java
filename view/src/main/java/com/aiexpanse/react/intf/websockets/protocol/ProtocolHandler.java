package com.aiexpanse.react.intf.websockets.protocol;

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
                break;
            }
        }
        return messageToReturn;
    }

    public void sendData(String id, Object data) throws IOException, EncodeException {
        ProtocolMessage message = new ProtocolMessage(id, MessageType.CONTENT, data);
        sendMessage(message);
    }

    public void sendError() throws IOException, EncodeException {
        sendMessage(new ProtocolMessage(null, MessageType.ERROR, null));
    }

    public void sendError(String reasonString) throws IOException, EncodeException {
        Map<String, Object> reason = new HashMap<>();
        reason.put("Reason", reasonString);
        sendMessage(new ProtocolMessage(null, MessageType.ERROR, reason));
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
