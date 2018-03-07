package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.dictionary.impl.DefaultGuiDomain;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;

import java.lang.reflect.Field;

public interface GuiDomainParserHelper {

    <T, W extends Widget, A extends Assembler<T, W>> A getAssembler(Class<?> clazz);

    DefaultGuiDomain<?> createDomain(Class<?> clazz);

    <W extends Widget> GuiRelationship<W, ?> createRelationship(Field field);

    <W extends Widget> GuiItem<W, ?> createItem(Field field);

}
