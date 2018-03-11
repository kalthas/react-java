package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.lang.IdentifiedBy;

import java.util.concurrent.atomic.AtomicInteger;

@DDD(name = "Domain")
public interface UISession extends IdentifiedBy<String> {

    AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    Widget getWidget(String uiPath);

}
