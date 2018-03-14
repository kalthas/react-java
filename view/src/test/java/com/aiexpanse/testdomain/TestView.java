package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIButton;
import com.aiexpanse.react.view.annotation.UIElement;
import com.aiexpanse.react.view.annotation.UIEventsHandler;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.api.Color;
import com.aiexpanse.react.view.impl.DefaultView;

@UIEventsHandler(clazz = TestViewHandler.class)
public class TestView extends DefaultView {

    @UIElement
    public TestForm form;

    @UIElement
    @UIWidget
    public TestForm form2;

    @UIButton
    public TestButton button;

    @UIButton(color = Color.PRIMARY)
    public TestButton button2;

}
