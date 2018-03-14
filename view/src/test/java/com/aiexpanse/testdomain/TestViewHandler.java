package com.aiexpanse.testdomain;

import com.aiexpanse.react.view.annotation.UIEvent;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.impl.AbstractEventsHandler;


public class TestViewHandler extends AbstractEventsHandler {

    @UIEvent(field = "button", type = EventType.ONCLICK)
    public void onButtonClick(TestView testView) {
        // ?
    }

}
