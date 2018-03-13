package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.GuiPath;

public class DefaultApplication extends AbstractWidgetContainer implements Application {

    @Override
    protected Boolean eagerDefault() {
        return true;
    }

    @Override
    public String getUIPath() {
        return GuiPath.PATH_SEP + getName();
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.APPLICATION;
    }

}
