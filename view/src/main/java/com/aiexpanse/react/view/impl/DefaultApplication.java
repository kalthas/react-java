package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Application;
import com.aiexpanse.react.view.api.Perspective;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetType;
import com.google.common.collect.Sets;

import java.util.Set;

public class DefaultApplication extends AbstractWidgetContainer implements Application {

    private static final Set<Class<? extends Widget>> LEGAL_CHILD_TYPES = Sets.newHashSet(Perspective.class);

    @Override
    protected Boolean eagerDefault() {
        return true;
    }

    @Override
    protected Set<Class<? extends Widget>> getAcceptedContentTypes() {
        return LEGAL_CHILD_TYPES;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.APPLICATION;
    }

}
