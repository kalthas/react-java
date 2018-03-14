package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractGuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiPath;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;

import java.util.concurrent.atomic.AtomicReference;

public class DefaultGuiRelationship<F, T> extends AbstractGuiMember<F, T> implements GuiRelationship<F, T> {

    private GuiDomain<T> endingDomain;
    private final AtomicReference<CharSequence> PATH = new AtomicReference<>();

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

    @Override
    public <E> GuiPath<F, E> append(GuiItem<T, E> item) {
        return path().append(item.path());
    }

    @Override
    public <E> GuiPath<F, E> append(GuiRelationship<T, E> relationship) {
        return null;
    }

    @Override
    public GuiPath<F, T> path() {
        return new DefaultGuiPath<>(this);
    }

    @Override
    public String toString() {
        CharSequence path = PATH.get();
        if (path == null) {
            PATH.compareAndSet(null, path().toString());
            path = PATH.get();
        }
        return path.toString();
    }

}
