package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;
import com.aiexpanse.dictionary.api.DDR;
import com.aiexpanse.lang.IdentifiedBy;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;

@DDD(name = "Widget")
public interface Widget extends IdentifiedBy<String> {

    @DDI(name = "Name")
    String getName();
    void setName(String name);

    @DDI(name = "Title")
    String getTitle();
    void setTitle(String title);

    @DDI(name = "Visible")
    Boolean getVisible();
    void setVisible(Boolean visible);

    @DDR(name = "Gui Domain", endingClass = GuiDomain.class)
    GuiDomain<?> getDomain();
    void setDomain(GuiDomain<?> domain);

    @DDI(name = "Widget Type")
    WidgetType getWidgetType();

    @DDI(name = "UI Path")
    String getUIPath();

    WidgetContainer getContainer();
    void setContainer(WidgetContainer widgetContainer);

}
