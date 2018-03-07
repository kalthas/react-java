package com.aiexpanse.react.view.factory.assembler.api;

import com.aiexpanse.react.view.factory.api.WidgetFactory;

public interface Assembler<T, W> {

    void populate(T t, W w);

    Assembler<T, W> with(WidgetFactory widgetFactory);

}
