package com.aiexpanse.react.view.factory.api;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class UIAnnotation {

    private Annotation annotation;
    private Assembler assembler;
    private Assembler fieldAssembler;

    public UIAnnotation(Annotation annotation, Assembler assembler, Assembler fieldAssembler) {
        this.annotation = annotation;
        this.assembler = assembler;
        this.fieldAssembler = fieldAssembler;
    }

    public void populate(Widget widget, Field field) {
        assembler.populate(annotation, widget);
        if (fieldAssembler != null) {
            fieldAssembler.populate(field, widget);
        }
    }

}
