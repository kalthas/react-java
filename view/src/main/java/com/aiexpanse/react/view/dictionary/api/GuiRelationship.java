package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Relationship;

@DDD(name = "Gui Relationship")
public interface GuiRelationship<F, T> extends GuiMember<F, T>, Relationship<F, T> {

    @Override
    GuiDomain<T> getEndingDomain();
    void setEndingDomain(GuiDomain<T> endingDomain);

}