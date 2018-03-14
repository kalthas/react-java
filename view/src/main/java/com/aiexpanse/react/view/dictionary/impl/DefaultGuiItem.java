package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractGuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiItem;

import java.util.Objects;

public class DefaultGuiItem<F, T> extends AbstractGuiMember<F, T> implements GuiItem<F, T> {

    private Class <T> widgetClass;

    @Override
    public Class<T> getType() {
        return widgetClass;
    }

    public void setType(Class<T> widgetClass) {
        this.widgetClass = widgetClass;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof DefaultGuiItem)) {
            return false;
        }
        DefaultGuiItem that = (DefaultGuiItem) other;
        return (definingDomain != null) &&
                (definingDomain.equals(that.definingDomain)) &&
                (widgetClass != null) &&
                (widgetClass.equals(that.widgetClass)) &&
                (name != null) &&
                (name.equals(that.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(definingDomain, widgetClass, name);
    }

}
