package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Field;
import com.aiexpanse.react.view.api.WidgetType;

import java.lang.reflect.Type;

public class DefaultField<T> extends DefaultWidget implements Field<T> {

    private T value;
    private Type valueType;

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public Type getValueType() {
        return valueType;
    }

    @Override
    public void setValueType(Type valueType) {
        this.valueType = valueType;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.FIELD;
    }

}
