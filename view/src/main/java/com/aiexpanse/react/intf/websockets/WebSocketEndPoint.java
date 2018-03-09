package com.aiexpanse.react.intf.websockets;

import com.aiexpanse.react.intf.websockets.guice.InjectConfigurator;
import com.aiexpanse.react.intf.websockets.protocol.ProtocolHandler;
import com.aiexpanse.react.intf.websockets.protocol.ProtocolMessage;
import com.aiexpanse.react.intf.websockets.protocol.ProtocolMessageDecoder;
import com.aiexpanse.react.intf.websockets.protocol.ProtocolMessageEncoder;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDateTime;

@ServerEndpoint(value = WebSocketEndPoint.PATH,
        subprotocols = {"react-view-ws"},
        configurator = InjectConfigurator.class,
        encoders = {ProtocolMessageEncoder.class},
        decoders = {ProtocolMessageDecoder.class}
)
public class WebSocketEndPoint {

    public static final String PATH = "/ws";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEndPoint.class);

    private static final String START_TIME = "Start Time";

    @Inject
    ProtocolHandler handler;

    @OnOpen
    public void onOpen(Session session) {
        session.getUserProperties().put(START_TIME, LocalDateTime.now());
        handler.init(session);
        LOGGER.info("WebSocket open");
    }

    /*
     * ???
     */
    @OnMessage
    public ProtocolMessage onMessage(ProtocolMessage protocolMessage) {
        LOGGER.debug("OnMessage: {}, {}", protocolMessage.getType(), protocolMessage.getPayload());
        return handler.handle(protocolMessage);
    }

    @OnError
    public void onError(Throwable t) throws IOException {
        if (t instanceof DecodeException) {
            LOGGER.error("Error decoding incoming message: {}", ((DecodeException)t).getText(), t);
        } else if (t instanceof SocketException) {
            LOGGER.debug("As long as it's socket exception, there's not much to say...", t);
            return;
        } else {
            LOGGER.error("Client WebSocket error: ", t);
        }
        try {
            handler.sendError();
        } catch (Exception e) {
            LOGGER.warn("Exception while send closing message", e);
        } finally {
            handler.closeSession(CloseReason.CloseCodes.PROTOCOL_ERROR);
        }
    }

    @OnClose
    public void onClose() {
        handler.sessionCleanup();
        LOGGER.info("WebSocket close");
    }

}
