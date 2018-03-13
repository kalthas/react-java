package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiPath;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;

public class DefaultWidget implements Widget {

    private String name;
    private String title;
    private Boolean visible = false;
    private GuiDomain<? extends Widget> domain;
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
        return Strings.isNullOrEmpty(title) ? name : title;
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
    @JsonIgnore
    public GuiDomain<? extends Widget> getDomain() {
        return domain;
    }

    @Override
    public void setDomain(GuiDomain<? extends Widget> domain) {
        this.domain = domain;
    }

    @Override
    public WidgetType getWidgetType() {
        return WidgetType.DEFAULT;
    }

    @Override
    public String getUIPath() {
        return container == null ? getName() : container.getUIPath() + GuiPath.PATH_SEP + getName();
    }

    @Override
    @JsonIgnore
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
