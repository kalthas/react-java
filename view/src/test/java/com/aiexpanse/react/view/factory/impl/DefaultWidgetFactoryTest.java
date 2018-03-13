package com.aiexpanse.react.view.factory.impl;

import com.aiexpanse.TestModule;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.testdomain.TestApplication;
import com.aiexpanse.testdomain.TestPerspective2;
import com.google.inject.Inject;
import com.google.inject.Injector;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DefaultWidgetFactoryTest {

    private static Injector injector = TestModule.getInjector();

    @Inject
    private WidgetFactory widgetFactory;

    DefaultWidgetFactoryTest() {
        injector.injectMembers(this);
    }

    @Test
    void test() {
        TestApplication testApplication = widgetFactory.createWidget(TestApplication.class);
        assertEquals(TestApplication.NAME, testApplication.getName());

        assertEquals(2, testApplication.perspective.getAllContents().size());
        assertEquals(1, testApplication.perspective2.getAllContents().size());

        assertTrue(testApplication.perspective.getEager());
        assertTrue(testApplication.perspective.getContentsLoaded());
        assertNotNull(testApplication.perspective.view);
        assertEquals(0, testApplication.perspective.view.getAllContents().size());

        assertEquals(TestPerspective2.TITLE, testApplication.perspective2.getDisplayName());
        assertTrue(testApplication.perspective2.getEager());
        assertTrue(testApplication.perspective2.getContentsLoaded());
        assertNotNull(testApplication.perspective2.view);
        assertEquals(4, testApplication.perspective2.view.getAllContents().size());
    }

}
