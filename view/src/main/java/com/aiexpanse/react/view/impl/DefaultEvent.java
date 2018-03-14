package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.internal.EventHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;

public class DefaultEvent implements Event {

    private WidgetContainer widgetContainer;
    private EventHandler eventHandler;

    public DefaultEvent(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public synchronized void bind(WidgetContainer widgetContainer) {
        if (this.widgetContainer != null) {
            throw new IllegalArgumentException("Already bound");
        }
        this.widgetContainer = widgetContainer;
    }

    @Override
    public EventType getType() {
        return eventHandler.getEventType();
    }

    @Override
    public String getField() {
        return eventHandler.getHandlingFieldName();
    }

    @Override
    @JsonIgnore
    public String getEventPath() {
        String handlingFieldName = eventHandler.getHandlingFieldName();
        StringBuilder sb = new StringBuilder(widgetContainer.getUIPath())
                .append(Widget.PATH_SEP);
        if (!Strings.isNullOrEmpty(handlingFieldName)) {
            sb.append(handlingFieldName).append(Widget.PATH_SEP);
        }
        sb.append(eventHandler.getEventType());
        return sb.toString();
    }

    @Override
    @JsonIgnore
    public WidgetContainer getSource() {
        return widgetContainer;
    }

    @Override
    public void handle() {
    }

}
