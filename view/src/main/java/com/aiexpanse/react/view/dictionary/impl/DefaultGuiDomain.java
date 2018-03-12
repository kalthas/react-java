package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractDomain;
import com.aiexpanse.react.view.factory.api.UIAnnotation;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

public class DefaultGuiDomain<T> extends AbstractDomain<T, GuiItem<T, ?>, GuiRelationship<T, ?>> implements GuiDomain<T> {

    private WidgetType widgetType;
    private List<UIAnnotation> uiAnnotations;

    @Override
    public WidgetType getWidgetType() {
        return widgetType;
    }

    @Override
    public void setWidgetType(WidgetType widgetType) {
        this.widgetType = widgetType;
    }

    @Override
    public Collection<GuiMember<T, ?>> getAllMembers() {
        return Lists.newArrayList(Iterables.concat(getAllItems(), getAllRelationships()));
    }

    @Override
    public GuiMember<T, ?> getMember(String name) {
        GuiMember<T, ?> member = getItem(name);
        if (member == null) {
            member = getRelationship(name);
        }
        return member;
    }

    @Override
    public List<UIAnnotation> getUIAnnotations() {
        return uiAnnotations;
    }

    @Override
    public void setUIAnnotations(List<UIAnnotation> uiAnnotations) {
        this.uiAnnotations = uiAnnotations;
    }

}
