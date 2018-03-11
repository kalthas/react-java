package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIContainer;
import com.aiexpanse.react.view.annotation.UIView;
import com.aiexpanse.react.view.impl.DefaultPerspective;

public class TestPerspective2 extends DefaultPerspective {

    @UIView
    @UIContainer(eager = true)
    public TestView view;

}
