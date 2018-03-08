package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;

import java.lang.reflect.Type;

@DDD(name = "Field")
public interface Field<T> extends Widget {

    T getValue();
    void setValue(T value);

    Type getValueType();
    void setValueType(Type valueType);

}
