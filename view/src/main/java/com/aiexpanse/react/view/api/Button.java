package com.aiexpanse.react.view.api;

import com.aiexpanse.dictionary.api.DDD;
import com.aiexpanse.dictionary.api.DDI;

@DDD(name = "Button")
public interface Button extends Widget {

    @DDI(name = "Color")
    Color getColor();
    void setColor(Color color);

}
