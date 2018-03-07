package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Member;

import java.lang.reflect.Field;

@DDD(name = "Gui Member")
public interface GuiMember<F, T> extends Member<F> {

    @Override
    GuiDomain<F> getDomain();
    void setDomain(GuiDomain<F> domain);

    Field getDeclaredField();
    void setDeclaredField(Field field);

    Integer getIndex();
    void setIndex(Integer index);

    <E> GuiPath<E, T> prepend(GuiRelationship<E, F> relationship);

    GuiPath<F, T> path();

}
