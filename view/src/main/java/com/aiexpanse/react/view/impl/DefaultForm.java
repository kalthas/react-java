package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Form;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultForm extends AbstractWidgetContainer implements Form {

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.FORM;
    }

    @Override
    protected Boolean eagerDefault() {
        return true;
    }

}
