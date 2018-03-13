package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.lang.IdentifiedBy;

import java.util.concurrent.atomic.AtomicInteger;

@DDD(name = "Domain")
public interface UISession extends IdentifiedBy<String> {

    AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    /*
     * get widget according to ui path, create one if not exists yet
     */
    Widget getWidget(String uiPath);

    /*
     * similar as getWidget, instead loadWidget will make sure return widget has contents loaded
     */
    Widget loadWidget(String uiPath);

}
