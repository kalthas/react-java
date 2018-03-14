package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;

import java.util.Collection;
import java.util.List;

@DDD(name = "Widget Container")
public interface WidgetContainer extends Widget {

    @DDI(name = "Eager Loading Children")
    Boolean getEager();
    void setEager(Boolean eager);

    Boolean getContentsLoaded();
    void setContentsLoaded(Boolean contentsLoaded);

    Collection<Widget> getAllContents();
    void clearAllContents();

    Widget getContent(String name);
    Widget getContentBySubPath(String subPath);

    void add(Widget widget);

    List<Event> getEvents();
    void setEvents(List<Event> events);

}
