package com.aiexpanse.react.view.sample;

import com.aiexpanse.react.view.annotation.UIApplication;
import com.aiexpanse.react.view.annotation.UIPerspective;
import com.aiexpanse.react.view.annotation.UIWidget;
import com.aiexpanse.react.view.impl.DefaultApplication;
import com.aiexpanse.react.view.sample.gallery.ui.GalleryPerspective;

@UIApplication(name = "SampleApplication")
public class SampleApplication extends DefaultApplication {

    @UIPerspective
    @UIWidget(title = "Gallery")
    public GalleryPerspective galleryPerspective;

}
