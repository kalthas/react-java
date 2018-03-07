package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;

public abstract class AbstractAssembler<T, W> implements Assembler<T, W> {

    protected WidgetFactory widgetFactory;

    @Override
    public Assembler<T, W> with(WidgetFactory widgetFactory) {
        this.widgetFactory = widgetFactory;
        return this;
    }

}
