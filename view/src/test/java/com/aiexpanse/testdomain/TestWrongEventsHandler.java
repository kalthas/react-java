package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIEvent;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.impl.AbstractEventsHandler;

public class TestWrongEventsHandler extends AbstractEventsHandler {

    @UIEvent(field = "nonexist", type = EventType.ONCLICK)
    public void whatever(TestFormWithWrongHandler from) {
        // nothing
    }

}
