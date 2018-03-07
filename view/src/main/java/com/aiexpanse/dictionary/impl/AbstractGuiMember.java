package com.aiexpanse.dictionary.impl;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiPath;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;

import java.lang.reflect.Field;

public abstract class AbstractGuiMember<F, T> implements GuiMember<F, T> {

    protected String name;
    protected GuiDomain<F> definingDomain;
    private Field declaredField;
    private Integer index;

    @Override
    public GuiDomain<F> getDomain() {
        return definingDomain;
    }

    @Override
    public void setDomain(GuiDomain<F> domain) {
        this.definingDomain = domain;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Field getDeclaredField() {
        return declaredField;
    }

    @Override
    public void setDeclaredField(Field declaredField) {
        this.declaredField = declaredField;
    }

    @Override
    public Integer getIndex() {
        return index;
    }

    @Override
    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public <E> GuiPath<E, T> prepend(GuiRelationship<E, F> relationship) {
        return path().prepend(relationship.path());
    }

}
