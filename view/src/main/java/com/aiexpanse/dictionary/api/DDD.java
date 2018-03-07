package com.aiexpanse.dictionary.api;

import java.lang.annotation.*;

/**
 * Created by lothair on 05/07/2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DDD {
    String name();
    Class<? extends Namespace> namespace() default Namespace.class;
    Class<?>[] domainImplClasses() default {};
}
