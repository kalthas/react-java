package com.aiexpanse.react.view.factory.checker.impl;

import com.aiexpanse.react.view.TypeConfig;
import com.aiexpanse.react.view.api.Widget;
import com.aiexpanse.react.view.api.WidgetContainer;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckResult;
import com.aiexpanse.react.view.factory.checker.api.WidgetClassCheckRule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

/*
 * This rule is to check annotation on field, e.g.
 *
 *   class MyApplication {
 *       @UIPerspective
 *       public MyPerspective perspective
 *   }
 *
 * UIPerspective is used to annotate a field, this rule check whether it's legal or not.
 *
 */
public class FieldAnnotationCheckRule implements WidgetClassCheckRule {

    @Override
    public WidgetClassCheckResult apply(Class<? extends Widget> widgetClass) {
        if (WidgetContainer.class.isAssignableFrom(widgetClass)) {
            for (Field field : widgetClass.getDeclaredFields()) {
                for (Annotation annotation : field.getAnnotations()) {
                    Class<? extends Annotation> annType = annotation.annotationType();
                    Set<Class<?>> types = TypeConfig.getCandidateTypesOfFieldAnnotation(annType);
                    if (!types.isEmpty()) {
                        if (!Modifier.isPublic(field.getModifiers())) {
                            String errorMessage = "Annotated field must be public, but [" + field + "] isn't";
                            return DefaultWidgetClassCheckResult.newInstanceWithError(errorMessage);
                        }
                        long match = types.stream()
                                .map(clazz -> clazz.isAssignableFrom(field.getType()))
                                .filter(b -> b)
                                .count();
                        if (match < 1) {
                            String errorMessage = "Annotation[" + annType.getName() + "] cannot annotate ["
                                    + field + "]";
                            return DefaultWidgetClassCheckResult.newInstanceWithError(errorMessage);
                        }
                    }
                }
            }
        }
        return null;
    }

}
