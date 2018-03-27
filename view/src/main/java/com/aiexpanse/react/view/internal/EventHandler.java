package com.aiexpanse.react.view.internal;

import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.dictionary.api.HandlerItem;

import java.lang.reflect.InvocationTargetException;

public class EventHandler {

    private HandlerItem handlerItem;
    private EventsHandler eventsHandler;

    public EventHandler(HandlerItem handlerItem, EventsHandler eventsHandler) {
        this.handlerItem = handlerItem;
        this.eventsHandler = eventsHandler;
    }

    public EventType getEventType() {
        return handlerItem.getEventType();
    }

    public String getHandlingFieldName() {
        return handlerItem.getName();
    }

    public void handle(WidgetContainer widgetContainer) {
        try {
            handlerItem.getDeclaredMethod().invoke(eventsHandler, widgetContainer);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
