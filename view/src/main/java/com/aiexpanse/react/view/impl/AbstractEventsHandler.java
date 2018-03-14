package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.EventsHandler;
import com.aiexpanse.react.view.api.WidgetContainer;

public abstract class AbstractEventsHandler implements EventsHandler {

    private WidgetContainer subject;

    public void setSubject(WidgetContainer subject) {
        this.subject = subject;
    }

}
