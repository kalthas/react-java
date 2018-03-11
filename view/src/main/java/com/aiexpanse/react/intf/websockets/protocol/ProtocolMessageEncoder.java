package com.aiexpanse.react.intf.websockets.protocol;

import com.aiexpanse.react.intf.websockets.codec.JSONEncoder;

public class ProtocolMessageEncoder extends JSONEncoder<ProtocolMessage> {

    @Override
    public void destroy() {
        // intentionally-blank
    }

}
