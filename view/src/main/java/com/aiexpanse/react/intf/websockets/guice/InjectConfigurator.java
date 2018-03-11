package com.aiexpanse.react.intf.websockets.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class InjectConfigurator extends ServerEndpointConfig.Configurator {

    public static final String INJECTOR = "injector";

    @Inject
    private static Injector injector;

    public <T> T getEndpointInstance(Class<T> endpointClass) {
        return injector.getInstance(endpointClass);
    }

    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put(INJECTOR, injector);
        super.modifyHandshake(sec, request, response);
    }

}
