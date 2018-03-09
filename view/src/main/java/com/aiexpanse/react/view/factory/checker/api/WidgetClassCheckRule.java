package com.aiexpanse.react.view.factory.checker.api;

import com.aiexpanse.react.view.api.Widget;

public interface WidgetClassCheckRule {

    WidgetClassCheckResult apply(Class<? extends Widget> widgetClass);

}
