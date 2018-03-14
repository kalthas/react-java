package com.aiexpanse.dictionary.api;

import java.lang.annotation.*;

/**
 * Created by lothair on 05/07/2017.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DDR {

    String name();
    String alias() default "";
    String description() default "";
    Class<?> endingClass();

}
