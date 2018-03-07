package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.api.Application;
import com.google.inject.Singleton;

@Singleton
public class UIApplicationAssembler<W extends Application> extends AbstractAssembler<UIApplication, W> {

    @Override
    public void populate(UIApplication uiApplication, W application) {
        application.setName(uiApplication.name());
    }

}
