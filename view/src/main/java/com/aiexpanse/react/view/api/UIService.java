package com.aiexpanse.react.view.api;

public interface UIService {

    /*
     * Get target widget by searching source widget with one which matches the uiPath
     */
    Widget getWidgetByUIPath(String uiPath, WidgetContainer source);

}
