package com.aiexpanse.react.view.api;

import com.aiexpanse.utils.TypeUtils;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public enum WidgetType {

    DEFAULT(Widget.class),
    APPLICATION(Application.class),
    PERSPECTIVE(Perspective.class),
    VIEW_GROUP(ViewGroup.class),
    VIEW(View.class),
    FORM(Form.class),
    BUTTON(Button.class, true),
    FIELD(Field.class, true);

    private boolean leafType;
    private Class<? extends Widget> widgetClass;

    WidgetType(Class<? extends Widget> widgetClass) {
        this(widgetClass, false);
    }

    WidgetType(Class<? extends Widget> widgetClass, boolean leafType) {
        this.widgetClass = widgetClass;
        this.leafType = leafType;
    }

    private static Set<WidgetType> LEAF_TYPES = Arrays.stream(values())
            .filter(widgetType -> widgetType.leafType)
            .collect(Collectors.toSet());
    public static boolean isLeafType(Class<?> widgetClass) {
        return LEAF_TYPES.stream()
                .anyMatch(widgetType -> widgetType.widgetClass.isAssignableFrom(widgetClass));
    }

    private static Map<Class<? extends Widget>, WidgetType> TYPE_BY_CLASS;
    static {
        TYPE_BY_CLASS = new HashMap<>();
        for (WidgetType widgetType : values()) {
            TYPE_BY_CLASS.put(widgetType.widgetClass, widgetType);
        }
    }
    private static List<WidgetType> TYPES;
    private static List<Class<? extends Widget>> WIDGET_CLASSES;
    static {
        TYPES = Lists.newArrayList(values());
        // TODO: below is a bad solution to find out sorted type array. (sort by specificity)
        for (int i=0; i<TYPES.size(); i++) {
            for (int j=i+1; j<TYPES.size(); j++) {
                if (TYPES.get(i).widgetClass.isAssignableFrom(TYPES.get(j).widgetClass)) {
                    WidgetType tmp = TYPES.get(i);
                    TYPES.set(i, TYPES.get(j));
                    TYPES.set(j, tmp);
                }
            }
        }
        WIDGET_CLASSES = TYPES.stream().map(widgetType -> widgetType.widgetClass).collect(Collectors.toList());
    }

    public static WidgetType getWidgetTypeByClass(Class<?> widgetClass) {
        return TYPE_BY_CLASS.get(TypeUtils.anySubType(WIDGET_CLASSES, widgetClass));
    }

}
