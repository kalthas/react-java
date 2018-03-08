package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.dictionary.impl.AbstractGuiMember;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiPath;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultGuiItem<F, T> extends AbstractGuiMember<F, T> implements GuiItem<F, T> {

    private Class <T> widgetClass;
    private final AtomicReference<CharSequence> PATH = new AtomicReference<>(null);

    public static <F, T> DefaultGuiItem<F, T> newInstance(
            GuiDomain<F> definingDomain,
            Class<T> widgetClass,
            String name) {
        DefaultGuiItem<F, T> item = new DefaultGuiItem<>();
        item.definingDomain = definingDomain;
        item.widgetClass = widgetClass;
        item.name = name;
        return item;
    }

    @Override
    public Class<T> getType() {
        return widgetClass;
    }

    public void setType(Class<T> widgetClass) {
        this.widgetClass = widgetClass;
    }

    @Override
    public GuiPath<F, T> path() {
        return new DefaultGuiPath<>(this);
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
