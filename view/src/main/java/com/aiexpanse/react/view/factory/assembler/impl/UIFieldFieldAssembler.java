package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.api.Field;
import com.google.inject.Singleton;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Singleton
public class UIFieldFieldAssembler<W extends Field<?>> extends AbstractAssembler<java.lang.reflect.Field, W> {

    @Override
    public void populate(java.lang.reflect.Field declaringField, W field) {
        Type genericType = declaringField.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return;
        }
        ParameterizedType fieldParamType = (ParameterizedType) genericType;
        Type fieldRawType = fieldParamType.getRawType();
        Type fieldGenericType = fieldParamType.getActualTypeArguments()[0];
        /*
         * class AField<T> extends DefaultField<List<T>>
         *
         * class BField<T> extends DefaultField<T>
         *
         * AField<String> => java.util.List<T>
         *
         * BField<List<String>> => java.util.List<String>
         *
         * BField<String> => java.lang.String
         */
        if (Field.class.isAssignableFrom((Class)fieldRawType) && !Field.class.equals(fieldRawType)) {
            Type superType = ((Class) fieldRawType).getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                Type superGenericType = ((ParameterizedType) superType).getActualTypeArguments()[0];
                if (superGenericType instanceof ParameterizedType && !(fieldGenericType instanceof ParameterizedType)) {
                    fieldGenericType = superGenericType;
                }
            }
        }
        field.setValueType(fieldGenericType);
    }

}
