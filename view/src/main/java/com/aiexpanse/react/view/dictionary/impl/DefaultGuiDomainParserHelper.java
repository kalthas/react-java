package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.dictionary.api.GuiDomainParserHelper;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;
import com.aiexpanse.react.view.factory.assembler.impl.UIApplicationAssembler;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class DefaultGuiDomainParserHelper implements GuiDomainParserHelper {

    private Injector injector;
    private Map<Class<?>, Assembler<?, ?>> assemblerByClass;

    @Inject
    public DefaultGuiDomainParserHelper(Injector injector) {
        this.injector = injector;
        initAssemblers();
    }

    private void initAssemblers() {
        assemblerByClass = new HashMap<>();
        assemblerByClass.put(UIApplication.class, injector.getInstance(UIApplicationAssembler.class));
    }


    @Override
    public <T, W extends Widget, A extends Assembler<T, W>> A getAssembler(Class<?> clazz) {
        return (A) assemblerByClass.get(clazz);
    }

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
