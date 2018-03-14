package com.aiexpanse.react.view.impl;

import com.aiexpanse.TestModule;
import com.aiexpanse.react.view.api.Event;
import com.aiexpanse.react.view.api.EventType;
import com.aiexpanse.react.view.api.UISession;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.dictionary.impl.AnnotationBasedGuiParser;
import com.aiexpanse.testdomain.TestApplication;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultUISessionTest {

    private static Injector injector = TestModule.getInjector();

    @Inject
    private UISession uiSession;

    @Inject
    private AnnotationBasedGuiParser parser;

    DefaultUISessionTest() {
        injector.injectMembers(this);
    }

    @Test
    void testGetEvent() {
        parser.parseDomain(TestApplication.class);
        uiSession.loadWidget("/TestApp");
        Widget view = uiSession.loadWidget("/TestApp/perspective/view");
        Event event = uiSession.getEventByPath("/TestApp/perspective/view/button/ONCLICK");
        assertSame(view, event.getSource());
        assertEquals(EventType.ONCLICK, event.getType());
    }

}