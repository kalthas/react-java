package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.ViewGroup;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultViewGroup extends AbstractWidgetContainer implements ViewGroup {

    protected Boolean eagerDefault() {
        return true;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.VIEW_GROUP;
    }

}
