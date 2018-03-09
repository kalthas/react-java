package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;

public class DefaultWidget implements Widget {

    private String name;
    private String title;
    private Boolean visible;
    private Integer index;
    private GuiDomain<?> domain;
    private WidgetContainer container;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Boolean getVisible() {
        return visible;
    }

    @Override
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    public GuiDomain<?> getDomain() {
        return domain;
    }

    @Override
    public void setDomain(GuiDomain<?> domain) {
        this.domain = domain;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.DEFAULT;
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
    public WidgetContainer getContainer() {
        return container;
    }

    @Override
    public void setContainer(WidgetContainer container) {
        this.container = container;
    }

    @Override
    public String key() {
        return name + container.getClass();
    }

}
