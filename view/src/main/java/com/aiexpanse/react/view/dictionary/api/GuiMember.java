package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Member;
import com.aiexpanse.react.view.factory.api.UIAnnotation;

import java.lang.reflect.Field;
import java.util.List;

@DDD(name = "Gui Member")
public interface GuiMember<F, T> extends Member<F> {

    @Override
    GuiDomain<F> getDomain();
    void setDomain(GuiDomain<F> domain);

    Field getDeclaredField();
    void setDeclaredField(Field field);

    List<UIAnnotation> getUIAnnotations();
    void setUIAnnotations(List<UIAnnotation> uiAnnotations);

    Integer getIndex();
    void setIndex(Integer index);

}
