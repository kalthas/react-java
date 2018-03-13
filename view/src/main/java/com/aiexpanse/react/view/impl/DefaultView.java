package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.View;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultView extends AbstractWidgetContainer implements View {

    @Override
    protected Boolean eagerDefault() {
        return false;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.VIEW;
    }

}
