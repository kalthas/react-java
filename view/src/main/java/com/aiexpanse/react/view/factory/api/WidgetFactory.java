package com.aiexpanse.react.view.factory.api;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;

public interface WidgetFactory {

    <W extends Widget> W createWidget(Class<W> widgetClass);
    <W extends Widget> W createWidget(GuiDomain<W> guiDomain);

}
