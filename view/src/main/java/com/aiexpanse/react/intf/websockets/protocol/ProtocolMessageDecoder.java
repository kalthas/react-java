package com.aiexpanse.react.intf.websockets.protocol;

import com.aiexpanse.react.intf.websockets.codec.codec.JSONDecoder;

public class ProtocolMessageDecoder extends JSONDecoder<ProtocolMessage> {

    @Override
    public void destroy() {
        // intentionally-blank
    }

}
