package com.aiexpanse.react.view.annotation;

import com.aiexpanse.react.view.api.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIEvent {

    String field() default "";

    EventType type();

}
