package com.aiexpanse.react.view.annotation;

import com.aiexpanse.react.view.api.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIField {

    FieldType type() default FieldType.TEXT;

}
