package com.aiexpanse.react.view.sample.gallery.ui;

import com.aiexpanse.react.view.annotation.UIViewGroup;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.impl.DefaultPerspective;
import com.aiexpanse.react.view.sample.gallery.ui.forms.Forms;

public class GalleryPerspective extends DefaultPerspective {

    @UIViewGroup
    @UIWidget(title = "Forms")
    public Forms forms;

}
