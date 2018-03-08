package com.aiexpanse.react.view.api;

public enum WidgetType {

    DEFAULT(Widget.class),
    APPLICATION(Application.class),
    PERSPECTIVE(Perspective.class),
    VIEW(View.class),
    FORM(Form.class),
    FIELD(Field.class);

    private Class<?> widgetClass;

    WidgetType(Class<?> widgetClass) {
        this.widgetClass = widgetClass;
    }

    public static boolean isLeafType(Class<?> widgetClass) {
        return false;
    }

    private static WidgetType[] TYPES;
    static {
        TYPES = values();
        // TODO: below is a bad solution to find out sorted type array. (sort by specificity)
        for (int i=0; i<TYPES.length; i++) {
            for (int j=i+1; j<TYPES.length; j++) {
                if (TYPES[i].widgetClass.isAssignableFrom(TYPES[j].widgetClass)) {
                    WidgetType tmp = TYPES[i];
                    TYPES[i] = TYPES[j];
                    TYPES[j] = tmp;
                }
            }
        }
    }

    public static WidgetType getWidgetTypeByClass(Class<?> widgetClass) {
        for (WidgetType widgetType : TYPES) {
            if (widgetType.widgetClass.isAssignableFrom(widgetClass)) {
                return widgetType;
            }
        }
        return DEFAULT;
    }

}
