package com.aiexpanse.react.view.factory.api;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;

import java.lang.annotation.Annotation;

public class UIAnnotation {

    private Annotation annotation;
    private Assembler assembler;

    public UIAnnotation(Annotation annotation, Assembler assembler) {
        this.annotation = annotation;
        this.assembler = assembler;
    }

    public void populate(Widget widget) {
        assembler.populate(annotation, widget);
    }

}
