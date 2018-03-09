package com.aiexpanse.react.view.factory.impl;

import com.aiexpanse.TestModule;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.testdomain.TestApplication;
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

        assertFalse(testApplication.perspective.getEager());
        assertFalse(testApplication.perspective.getContentsLoaded());
        assertNull(testApplication.perspective.view);

        assertTrue(testApplication.perspective2.getEager());
        assertTrue(testApplication.perspective2.getContentsLoaded());
        assertNotNull(testApplication.perspective2.view);
    }

}
