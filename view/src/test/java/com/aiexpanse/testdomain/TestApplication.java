package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.annotation.UIPerspective;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.impl.DefaultApplication;

@UIApplication(name = TestApplication.NAME)
public class TestApplication extends DefaultApplication {

    public static final String NAME = "TestApp";

    @UIPerspective
    public TestPerspective perspective;

    @UIPerspective
    @UIWidget(title = TestPerspective2.TITLE)
    public TestPerspective2 perspective2;

}
