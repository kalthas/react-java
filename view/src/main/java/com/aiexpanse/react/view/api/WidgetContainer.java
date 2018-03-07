package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;

import java.util.Collection;

@DDD(name = "Widget Container")
public interface WidgetContainer extends Widget {

    Collection<Widget> getAllContents();
    void clearAllContents();

    Widget getContent(String name);

    void add(Widget widget);

    Handler getHandler();
    void setHandler(Handler handler);

}
