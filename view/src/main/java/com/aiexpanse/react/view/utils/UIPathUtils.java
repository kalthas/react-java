package com.aiexpanse.react.view.utils;

import com.aiexpanse.lang.Pair;
import com.aiexpanse.react.view.dictionary.api.GuiPath;

import java.util.Objects;

public final class UIPathUtils {

    private UIPathUtils() {
        // prevent construction
    }

    /*
     * Root part of the ui path, say for /someApp it's /someApp, but
     * for /someApp/p1 it's /someApp
     */
    public static String getRootUIPath(String uiPath) {
        Objects.requireNonNull(uiPath, "cannot get root uiPath from null");
        int i = uiPath.indexOf(GuiPath.PATH_SEP, 1);
        return i > -1 ? uiPath.substring(0, i) : uiPath;
    }

    /*
     * Root name of the ui path, say for /someApp it's someApp, but
     * for /someApp/p1 it's still someApp
     */
    public static String getRootName(String uiPath) {
        String rootUIPath = getRootUIPath(uiPath);
        return rootUIPath.startsWith(GuiPath.PATH_SEP) ? rootUIPath.substring(1) : rootUIPath;
    }

    public static Pair<String, String> split(String uiPath) {
        Objects.requireNonNull(uiPath, "cannot split null");
        int i = uiPath.indexOf(GuiPath.PATH_SEP);
        String head = i > -1 ? uiPath.substring(0, i) : uiPath;
        String remaining = (i > -1 && uiPath.length()-1 != i) ? uiPath.substring(i+1) : null;
        return new Pair<>(head, remaining);
    }

    public static String getSubPath(String baseUIPath, String uiPath) {
        if (baseUIPath != null && uiPath != null && uiPath.startsWith(baseUIPath + GuiPath.PATH_SEP)) {
            return uiPath.substring(baseUIPath.length() + 1);
        }
        return null;
    }

    public static boolean isParentUIPath(String containerUIPath, String uiPath) {
        Objects.requireNonNull(containerUIPath, "container uiPath is null");
        Objects.requireNonNull(uiPath, "uiPath is null");
        return uiPath.startsWith(containerUIPath + GuiPath.PATH_SEP);
    }

}
