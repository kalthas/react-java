package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultApplication extends AbstractWidgetContainer implements Application {

    @Override
    protected Boolean eagerDefault() {
        return true;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.APPLICATION;
    }

}
