package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultApplication extends AbstractWidgetContainer implements Application {

    @Override
    public String getUIPath() {
        return PATH_SEP + getName();
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.APPLICATION;
    }

}
