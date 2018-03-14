package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIEventHandler;
import com.aiexpanse.react.view.api.Handler;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class UIEventHandlerAssembler<W extends WidgetContainer> extends AbstractAssembler<UIEventHandler, W> {

    @Inject
    private Injector injector;

    @Override
    public void populate(UIEventHandler uiEventHandler, W widgetContainer) {
        Class<? extends Handler> clazz = uiEventHandler.clazz();
        Handler handler = injector.getInstance(clazz);
        widgetContainer.setHandler(handler);
    }

}
