package com.aiexpanse.react.view.impl;

import com.aiexpanse.react.view.TypeConfig;
import com.aiexpanse.react.view.api.Handler;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.utils.TypeUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractWidgetContainer extends DefaultWidget implements WidgetContainer {

    private List<Widget> contents = new CopyOnWriteArrayList<>();
    private Boolean contentsLoaded = false;
    private Boolean eager = eagerDefault();
    private Handler handler;

    protected Boolean eagerDefault() {
        return false;
    }

    @Override
    public Boolean getEager() {
        return eager;
    }

    @Override
    public void setEager(Boolean eager) {
        this.eager = eager;
    }


    @Override
    public Boolean getContentsLoaded() {
        return contentsLoaded;
    }

    @Override
    public void setContentsLoaded(Boolean contentsLoaded) {
        this.contentsLoaded = contentsLoaded;
    }

    @Override
    public Collection<Widget> getAllContents() {
        return contents;
    }

    @Override
    public void clearAllContents() {
        contents.clear();
    }

    @Override
    public Widget getContent(String name) {
        for (Widget content : contents) {
            if (name.equals(content.getName())) {
                return content;
            }
        }
        return null;
    }

    @Override
    public void add(Widget content) {
        checkAcceptance(content);
        checkNonExistence(content);
        content.setContainer(this);
        contents.add(content);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public void setHandler(Handler handler) {
        this.handler = handler;
        if (handler instanceof AbstractHandler) {
            ((AbstractHandler)handler).setSubject(this);
        }
    }

    private void checkAcceptance(Widget content) {
        Set<Class<? extends Widget>> acceptedContentTypes = TypeConfig.getAcceptableContentTypes(getWidgetType());
        if (TypeUtils.anySubType(acceptedContentTypes, content.getClass()) != null) {
            return;
        }
        throw new IllegalArgumentException("Content: " + content + " is not a acceptable type of " + this);
    }

    private void checkNonExistence(Widget content) {
        for (Widget widget : contents) {
            if (widget.getName().equals(content.getName())) {
                throw new IllegalArgumentException("Content with name[" + widget.getName() + "] already exists");
            }
        }
    }

}
