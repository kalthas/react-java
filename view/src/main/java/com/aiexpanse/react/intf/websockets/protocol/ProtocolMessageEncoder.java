package com.aiexpanse.react.intf.websockets.protocol;

import com.aiexpanse.react.intf.websockets.codec.codec.JSONEncoder;

import javax.websocket.EndpointConfig;

public class ProtocolMessageEncoder extends JSONEncoder<ProtocolMessage> {

    @Override
    public void init(EndpointConfig endpointConfig) {
        // intentionally-blank
    }

    @Override
    public void destroy() {
        // intentionally-blank
    }

}
