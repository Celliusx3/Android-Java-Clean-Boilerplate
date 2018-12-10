package com.app.cellstudio.presentation.presentation.util.image;

/**
 * Created by coyan on 13/09/2018.
 */

public class BaseImageLoader {

    private static ImageLoader sInstance;

    public static ImageLoader getInstance() {
        if (sInstance == null) {
            sInstance = new PicassoImageLoader();
        }
        return sInstance;
    }
}
