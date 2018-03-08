package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Perspective;
import com.aiexpanse.react.view.api.View;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetType;
import com.google.common.collect.Sets;

import java.util.Set;

public class DefaultPerspective extends AbstractWidgetContainer implements Perspective {

    private static final Set<Class<? extends Widget>> LEGAL_CHILD_TYPES = Sets.newHashSet(View.class);

    @Override
    protected Set<Class<? extends Widget>> getAcceptedContentTypes() {
        return LEGAL_CHILD_TYPES;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.PERSPECTIVE;
    }

}
