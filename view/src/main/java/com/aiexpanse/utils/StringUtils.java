package com.aiexpanse.utils;

public class StringUtils {

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

}
