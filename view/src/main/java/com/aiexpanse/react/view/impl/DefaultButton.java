package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Button;
import com.aiexpanse.react.view.api.Color;
import com.aiexpanse.react.view.api.WidgetType;

public class DefaultButton extends DefaultWidget implements Button {

    private Color color;

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.BUTTON;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

}
