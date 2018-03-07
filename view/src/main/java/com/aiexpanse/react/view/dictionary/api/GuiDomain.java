package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.react.view.api.WidgetType;
import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;
import com.aiexpanse.dictionary.api.Domain;

import java.util.Collection;

@DDD(name = "Gui Domain")
public interface GuiDomain<T> extends Domain<T, GuiItem<T, ?>, GuiRelationship<T, ?>> {

    @DDI(name = "Widget Type")
    WidgetType getWidgetType();
    void setWidgetType(WidgetType widgetType);

    Collection<GuiMember<T, ?>> getAllMembers();

    GuiMember<T, ?> getMember(String name);

}
