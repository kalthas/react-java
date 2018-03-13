package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Field;
import com.aiexpanse.react.view.api.FieldType;
import com.aiexpanse.react.view.api.WidgetType;

import java.lang.reflect.Type;

public class DefaultField<T> extends DefaultWidget implements Field<T> {

    private T value;
    private Type valueType;
    private FieldType fieldType;

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
    public FieldType getType() {
        return fieldType;
    }

    @Override
    public void setType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.FIELD;
    }

}
