package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIEventsHandler;
import com.aiexpanse.react.view.impl.DefaultForm;

@UIEventsHandler(clazz = TestWrongEventsHandler.class)
public class TestFormWithWrongHandler extends DefaultForm {
}
