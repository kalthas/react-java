package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.TestModule;
import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.testdomain.DummyView;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class AnnotationBasedGuiParserTest {

    private static Injector injector = TestModule.getInjector();

    @Inject
    private AnnotationBasedGuiParser parser;

    AnnotationBasedGuiParserTest() {
        injector.injectMembers(this);
    }

    @Test
    void parseDomain() {
        GuiDomain<DummyView> domain = parser.parseDomain(DummyView.class);
        assertNotNull(domain);
        Collection<GuiItem<DummyView, ?>> allItems = domain.getAllItems();
        assertEquals(2, allItems.size());

    }

}