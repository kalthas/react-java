package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIContainer;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.google.inject.Singleton;

@Singleton
public class UIContainerAssembler<W extends WidgetContainer> extends AbstractAssembler<UIContainer, W> {

    @Override
    public void populate(UIContainer uiContainer, W widgetContainer) {
        widgetContainer.setEager(uiContainer.eager());
    }

}
