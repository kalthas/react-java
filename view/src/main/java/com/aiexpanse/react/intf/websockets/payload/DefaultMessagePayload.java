package com.aiexpanse.react.intf.websockets.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public class DefaultMessagePayload {

    private String uiPath;
    private Object content;

    public DefaultMessagePayload(Map map) {
        Objects.requireNonNull(map.get("uipath"), "uipath is null");
        this.uiPath = (String)map.get("uipath");
        this.content = map.get("content");
    }

    @JsonCreator
    public DefaultMessagePayload(@JsonProperty("uipath") String uiPath,
                                 @JsonProperty("content") Object content) {
        this.uiPath = uiPath;
        this.content = content;
    }

    public String getUIPath() {
        return uiPath;
    }

    public Object getContent() {
        return content;
    }

}
