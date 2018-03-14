package com.aiexpanse.react.view.annotation;

import com.aiexpanse.react.view.api.EventClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIEvent {
    Class<? extends EventClass> eventClass() default EventClass.class;
}
