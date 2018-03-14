package com.aiexpanse.utils;

public class GuiceUtils {

    private static final String GUICE_PROXY_FLAG = "$EnhancerByGuice$";
    
    public static Class getRawClassFromProxy(Class clazz) {
        boolean isGuiceProxy = clazz.getName().contains(GUICE_PROXY_FLAG);
        if (isGuiceProxy) {
            return getRawClassFromProxy(clazz.getSuperclass());
        }
        return clazz;
    }

}