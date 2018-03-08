package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Handler;
import com.aiexpanse.react.view.api.WidgetContainer;

public abstract class AbstractHandler implements Handler {

    private WidgetContainer subject;

    public void setSubject(WidgetContainer subject) {
        this.subject = subject;
    }

}
