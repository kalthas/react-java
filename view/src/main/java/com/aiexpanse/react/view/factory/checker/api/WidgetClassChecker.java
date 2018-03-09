package com.aiexpanse.react.view.factory.checker.api;

import com.aiexpanse.react.view.api.Widget;

public interface WidgetClassChecker {

    void check(Class<? extends Widget> widgetClass);

    void registerRule(WidgetClassCheckRule rule);

}
