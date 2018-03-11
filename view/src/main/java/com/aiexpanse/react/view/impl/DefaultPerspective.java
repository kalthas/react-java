package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Perspective;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultPerspective extends AbstractWidgetContainer implements Perspective {

    protected Boolean eagerDefault() {
        return true;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.PERSPECTIVE;
    }

}
