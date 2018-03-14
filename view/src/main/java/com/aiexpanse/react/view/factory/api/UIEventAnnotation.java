package com.aiexpanse.react.view.factory.api;

import com.aiexpanse.react.view.api.EventClass;

import java.lang.annotation.Annotation;

public class UIEventAnnotation {

    private Annotation annotation;
    private Class<? extends EventClass> eventClass;

    public UIEventAnnotation(Annotation annotation,
                             Class<? extends EventClass> eventClass) {
        this.annotation = annotation;
        this.eventClass = eventClass;
    }

}
