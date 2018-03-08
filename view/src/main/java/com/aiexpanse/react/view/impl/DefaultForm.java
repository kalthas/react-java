package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Field;
import com.aiexpanse.react.view.api.Form;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetType;
import com.google.common.collect.Sets;

import java.util.Set;

public class DefaultForm extends AbstractWidgetContainer implements Form {

    private static final Set<Class<? extends Widget>> LEGAL_CHILD_TYPES = Sets.newHashSet(Field.class);

    @Override
    protected Set<Class<? extends Widget>> getAcceptedContentTypes() {
        return LEGAL_CHILD_TYPES;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.FORM;
    }

}
