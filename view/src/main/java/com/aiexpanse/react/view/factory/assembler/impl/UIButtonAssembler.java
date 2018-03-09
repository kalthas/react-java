package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIButton;
import com.aiexpanse.react.view.api.Button;
import com.google.inject.Singleton;

@Singleton
public class UIButtonAssembler<W extends Button> extends AbstractAssembler<UIButton, W> {

    @Override
    public void populate(UIButton uiButton, W button) {
        button.setColor(uiButton.color());
    }

}
