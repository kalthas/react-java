package com.aiexpanse.react.view.factory.assembler.impl;

import com.aiexpanse.TestModule;
import com.aiexpanse.react.view.api.Color;
import com.aiexpanse.react.view.factory.api.WidgetFactory;
import com.aiexpanse.testdomain.TestView;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UIButtonAssemblerTest {

    private static Injector injector = TestModule.getInjector();

    @Inject
    private WidgetFactory widgetFactory;

    UIButtonAssemblerTest() {
        injector.injectMembers(this);
    }

    @Test
    void test() {
        TestView view = widgetFactory.createWidget(TestView.class);
        assertEquals(Color.DEFAULT, view.button.getColor());
        assertEquals(Color.PRIMARY, view.button2.getColor());
    }

}