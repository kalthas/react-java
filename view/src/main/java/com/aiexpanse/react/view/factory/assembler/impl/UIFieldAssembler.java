package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIField;
import com.aiexpanse.react.view.api.Field;
import com.google.inject.Singleton;

@Singleton
public class UIFieldAssembler<W extends Field<?>> extends AbstractAssembler<UIField, W> {

    @Override
    public void populate(UIField uiField, W field) {
        field.setType(uiField.type());
    }

}
