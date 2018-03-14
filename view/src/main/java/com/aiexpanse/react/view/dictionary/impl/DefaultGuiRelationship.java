package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractGuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;

public class DefaultGuiRelationship<F, T> extends AbstractGuiMember<F, T> implements GuiRelationship<F, T> {

    private GuiDomain<T> endingDomain;

    public static <F, T> DefaultGuiRelationship<F, T> newInstance(
            GuiDomain<F> definingDomain,
            GuiDomain<T> endingDomain,
            String name) {
        DefaultGuiRelationship<F, T> relationship = new DefaultGuiRelationship<>();
        relationship.definingDomain = definingDomain;
        relationship.endingDomain = endingDomain;
        relationship.name = name;
        return relationship;
    }

    @Override
    public GuiDomain<T> getEndingDomain() {
        return endingDomain;
    }

    @Override
    public void setEndingDomain(GuiDomain<T> endingDomain) {
        this.endingDomain = endingDomain;
    }

}
