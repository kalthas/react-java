package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParserHelper;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.lang.reflect.Field;

@Singleton
public class DefaultGuiDomainParserHelper implements GuiDomainParserHelper {

    @Inject
    private Injector injector;

    @Override
    public DefaultGuiDomain<?> createDomain(Class<?> clazz) {
        return injector.getInstance(DefaultGuiDomain.class);
    }

    @Override
    public <W extends Widget> GuiRelationship<W, ?> createRelationship(Field field) {
        return injector.getInstance(DefaultGuiRelationship.class);
    }

    @Override
    public <W extends Widget> GuiItem<W, ?> createItem(Field field) {
        return injector.getInstance(DefaultGuiItem.class);
    }

}
