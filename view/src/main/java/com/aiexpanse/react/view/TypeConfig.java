package com.aiexpanse.react.view;

import com.aiexpanse.react.view.annotation.*;
import com.aiexpanse.react.view.api.*;
import com.aiexpanse.react.view.factory.api.UIAnnotation;
import com.aiexpanse.react.view.factory.assembler.api.Assembler;
import com.aiexpanse.react.view.factory.assembler.impl.*;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class TypeConfig {

    private static final AtomicReference<TypeConfig> SELF = new AtomicReference<>();
    private static final Assembler DEFAULT_ASSEMBLER = new NoOpAssembler();
    private final Set<Class<? extends Annotation>> ALL_UI_ANNOTATIONS;

    @Inject
    public TypeConfig(Injector injector) {
        initAcceptableAnnotationOnField();
        initAcceptableChildTypes();
        initAssemblerByClass(injector);
        initFieldAssemblerByClass(injector);
        ALL_UI_ANNOTATIONS = ACCEPTABLE_ANNOTATION_ON_FIELD.keySet();
        SELF.compareAndSet(null, this);
    }

    private final Map<Class<? extends Annotation>, Set<Class<?>>> ACCEPTABLE_ANNOTATION_ON_FIELD = new HashMap<>();
    private void initAcceptableAnnotationOnField() {
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIPerspective.class, Sets.newHashSet(Perspective.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIViewGroup.class, Sets.newHashSet(ViewGroup.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIView.class, Sets.newHashSet(View.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIButton.class, Sets.newHashSet(Button.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIForm.class, Sets.newHashSet(Form.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIElement.class, Sets.newHashSet(Element.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIField.class, Sets.newHashSet(Field.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIContainer.class, Sets.newHashSet(WidgetContainer.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIEventHandler.class, Sets.newHashSet(WidgetContainer.class));
        ACCEPTABLE_ANNOTATION_ON_FIELD.put(UIWidget.class, Sets.newHashSet(Widget.class));
    }

    private final Map<WidgetType, Set<Class<? extends Widget>>> ACCEPTABLE_CHILD_TYPES = new HashMap<>();
    private void initAcceptableChildTypes() {
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.APPLICATION, Sets.newHashSet(Perspective.class));
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.PERSPECTIVE, Sets.newHashSet(View.class, ViewGroup.class));
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.VIEW_GROUP, Sets.newHashSet(View.class));
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.VIEW, Sets.newHashSet(Element.class, Button.class));
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.FORM, Sets.newHashSet(Field.class, Button.class));
        ACCEPTABLE_CHILD_TYPES.put(WidgetType.DEFAULT, Sets.newHashSet());
    }

    private final Map<Class<? extends Annotation>, Assembler> ASSEMBLER_BY_CLASS = new HashMap<>();
    private void initAssemblerByClass(Injector injector) {
        ASSEMBLER_BY_CLASS.put(UIApplication.class, injector.getInstance(UIApplicationAssembler.class));
        ASSEMBLER_BY_CLASS.put(UIButton.class, injector.getInstance(UIButtonAssembler.class));
        ASSEMBLER_BY_CLASS.put(UIField.class, injector.getInstance(UIFieldAssembler.class));
        ASSEMBLER_BY_CLASS.put(UIContainer.class, injector.getInstance(UIContainerAssembler.class));
        ASSEMBLER_BY_CLASS.put(UIEventHandler.class, injector.getInstance(UIEventHandlerAssembler.class));
        ASSEMBLER_BY_CLASS.put(UIWidget.class, injector.getInstance(UIWidgetAssembler.class));
    }
    private final Map<Class<? extends Annotation>, Assembler> FIELD_ASSEMBLER_BY_CLASS = new HashMap<>();
    private void initFieldAssemblerByClass(Injector injector) {
        FIELD_ASSEMBLER_BY_CLASS.put(UIField.class, injector.getInstance(UIFieldFieldAssembler.class));
    }

    public static Set<Class<?>> getCandidateTypesOfFieldAnnotation(Class<? extends Annotation> annType) {
        return SELF.get().ACCEPTABLE_ANNOTATION_ON_FIELD.get(annType);
    }

    public static Set<Class<? extends Widget>> getAcceptableContentTypes(WidgetType widgetType) {
        Set<Class<? extends Widget>> types = SELF.get().ACCEPTABLE_CHILD_TYPES.get(widgetType);
        if (types == null) {
            throw new RuntimeException("Acceptable child types is not configured for widget type [" + widgetType + "]");
        }
        return types;
    }

    public static Assembler getAssembler(Class<? extends Annotation> annotationClass) {
        return SELF.get().ASSEMBLER_BY_CLASS.get(annotationClass);
    }

    public static Assembler getFieldAssembler(Class<? extends Annotation> annotationClass) {
        return SELF.get().FIELD_ASSEMBLER_BY_CLASS.get(annotationClass);
    }

    public static UIAnnotation getUIAnnotation(Annotation annotation) {
        TypeConfig typeConfig = SELF.get();
        if (typeConfig.ALL_UI_ANNOTATIONS.contains(annotation.annotationType())) {
            Assembler assembler = getAssembler(annotation.annotationType());
            if (assembler == null) {
                assembler = DEFAULT_ASSEMBLER;
            }
            Assembler fieldAssembler = getFieldAssembler(annotation.annotationType());
            return new UIAnnotation(annotation, assembler, fieldAssembler);
        }
        return null;
    }

}
