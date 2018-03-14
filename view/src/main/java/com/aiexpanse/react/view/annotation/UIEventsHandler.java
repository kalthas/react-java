package com.aiexpanse.react.view.annotation;

import com.aiexpanse.react.view.api.EventsHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UIEventsHandler {
    Class<? extends EventsHandler> clazz();
}
