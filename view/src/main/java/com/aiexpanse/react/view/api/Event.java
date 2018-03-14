package com.aiexpanse.react.view.api;

public interface Event {

    EventType getType();

    String getEventPath();

    String getField();

    void bind(WidgetContainer widgetContainer);
    WidgetContainer getSource();

    void handle();

}
