package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIView;
import com.aiexpanse.react.view.annotation.UIViewGroup;
import com.aiexpanse.react.view.impl.DefaultPerspective;

public class TestPerspective extends DefaultPerspective {

    @UIView
    public TestView view;

    @UIViewGroup
    public TestViewGroup group;

}
