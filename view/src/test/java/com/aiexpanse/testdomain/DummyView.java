package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIElement;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.impl.DefaultView;

public class DummyView extends DefaultView {

    @UIElement
    public DummyForm form;

    @UIElement
    @UIWidget
    public DummyForm form2;

}
