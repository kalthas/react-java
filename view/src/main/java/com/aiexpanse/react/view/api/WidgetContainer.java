package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;

import java.util.Collection;

@DDD(name = "Widget Container")
public interface WidgetContainer extends Widget {

    @DDI(name = "Eager Loading Children")
    Boolean getEager();
    void setEager(Boolean eager);

    Collection<Widget> getAllContents();
    void clearAllContents();

    Widget getContent(String name);

    void add(Widget widget);

    Handler getHandler();
    void setHandler(Handler handler);

}
