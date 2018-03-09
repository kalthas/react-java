package com.aiexpanse.react.intf.websockets.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;

import javax.websocket.server.ServerEndpointConfig;

public class InjectConfigurator extends ServerEndpointConfig.Configurator {

    @Inject
    private static Injector injector;

    public <T> T getEndpointInstance(Class<T> endpointClass) {
        return injector.getInstance(endpointClass);
    }

}
