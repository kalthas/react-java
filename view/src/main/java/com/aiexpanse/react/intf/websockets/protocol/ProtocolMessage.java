package com.aiexpanse.react.intf.websockets.protocol;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ProtocolMessage {

    private Object payload;
    private String id;
    private MessageType type;

    public ProtocolMessage() {
        // intentionally-blank
    }

    public ProtocolMessage(String id, MessageType type, Object payload) {
        this.id = id;
        this.type = type;
        this.payload = payload;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProtocolMessage[Type=" +
                type +
                ", payload=" +
                payload +
                "]";
    }

}
