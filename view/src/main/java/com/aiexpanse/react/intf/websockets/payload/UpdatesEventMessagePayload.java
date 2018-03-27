package com.aiexpanse.react.intf.websockets.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UpdatesEventMessagePayload {

    private List<UpdateMessagePayload> updateEvents = new ArrayList<>();
    private DefaultMessagePayload event;

    public UpdatesEventMessagePayload(Map map) {
        Objects.requireNonNull(map.get("updates"));
        Objects.requireNonNull(map.get("event"));
        for (Object update : (List) map.get("updates")) {
            this.updateEvents.add(new UpdateMessagePayload((Map)update));
        }
        this.event = new DefaultMessagePayload((Map)map.get("event"));
    }

    public DefaultMessagePayload getEvent() {
        return event;
    }

    public List<UpdateMessagePayload> getUpdateEvents() {
        return updateEvents;
    }

}
