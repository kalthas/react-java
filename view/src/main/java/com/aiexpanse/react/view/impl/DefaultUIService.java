package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.UIService;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.utils.UIPathUtils;
import com.google.common.base.Strings;
import com.google.inject.Singleton;

@Singleton
public class DefaultUIService implements UIService {

    @Override
    public Widget getWidgetByUIPath(String uiPath, WidgetContainer source) {
        if (Strings.isNullOrEmpty(uiPath) || source == null) {
            return null;
        }
        String sourceUIPath = source.getUIPath();
        if (uiPath.equals(sourceUIPath)) {
            return source;
        }
        String subPath = UIPathUtils.getSubPath(sourceUIPath, uiPath);
        if (Strings.isNullOrEmpty(subPath)) {
            return null;
        }
        return source.getContentBySubPath(subPath);
    }

}
