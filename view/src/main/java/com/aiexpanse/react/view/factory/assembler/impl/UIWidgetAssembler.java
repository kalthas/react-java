package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.api.Widget;
import com.google.common.base.Strings;
import com.google.inject.Singleton;

@Singleton
public class UIWidgetAssembler<W extends Widget> extends AbstractAssembler<UIWidget, W> {

    @Override
    public void populate(UIWidget uiWidget, W widget) {
        widget.setVisible(uiWidget.visible());
        if (!Strings.isNullOrEmpty(uiWidget.displayName())) {
            widget.setDisplayName(uiWidget.displayName());
        }
    }

}
