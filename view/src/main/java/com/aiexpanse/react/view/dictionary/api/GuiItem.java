package com.aiexpanse.react.view.dictionary.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.Item;

@DDD(name = "Gui Item")
public interface GuiItem<F, T> extends GuiMember<F, T>, Item<F, T> {
}
