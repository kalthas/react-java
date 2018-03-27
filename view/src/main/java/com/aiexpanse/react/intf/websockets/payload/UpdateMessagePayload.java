package com.aiexpanse.react.intf.websockets.payload;

import java.util.Map;
import java.util.Objects;

public class UpdateMessagePayload {

    private String uiPath;
    private String propName;
    private Object value;

    public UpdateMessagePayload(Map map) {
        Objects.requireNonNull(map.get("uipath"), "uipath is null");
        Objects.requireNonNull(map.get("propName"), "propName is null");
        this.uiPath = (String)map.get("uipath");
        this.propName = (String)map.get("propName");
        this.value = map.get("value");
    }

    public String getUiPath() {
        return uiPath;
    }

    public String getPropName() {
        return propName;
    }

    public Object getValue() {
        return value;
    }

}
