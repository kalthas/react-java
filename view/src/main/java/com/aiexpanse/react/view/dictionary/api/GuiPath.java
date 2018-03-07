package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.Path;

import java.util.List;

public interface GuiPath<F, T> extends Path<F, T, GuiItem<?, T>, GuiRelationship<?, ?>>, Comparable<GuiPath<?, ?>> {

    @Override
    GuiDomain<F> getStartingDomain();

    <E> GuiPath<F, E> append(GuiPath<? extends T, E> path);

    <S> GuiPath<S, T> prepend(GuiPath<S, F> path);

    <S> GuiPath<S, F> getParent();

    String toPathString();

    void addRelationship(GuiRelationship<?, ?> guiRelationship);

    void addRelationships(List<GuiRelationship<?, ?>> guiRelationships);

}