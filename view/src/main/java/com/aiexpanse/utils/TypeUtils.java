package com.aiexpanse.utils;

public class TypeUtils {

    public static <K extends Class<?>> K anySubType(Iterable<K> types, Class<?> type) {
        for (K aType : types) {
            if (aType.isAssignableFrom(type)) {
                return aType;
            }
        }
        return null;
    }

}
