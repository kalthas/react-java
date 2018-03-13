package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;

import java.lang.reflect.Type;

@DDD(name = "Field")
public interface Field<T> extends Widget {

    @DDI(name = "Value")
    T getValue();
    void setValue(T value);

    @DDI(name = "Value Type")
    Type getValueType();
    void setValueType(Type valueType);

    @DDI(name = "Field Type")
    FieldType getType();
    void setType(FieldType fieldType);

}
