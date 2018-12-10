package com.app.cellstudio.data.mapper;

import com.app.cellstudio.data.entity.ImageDataModel;
import com.app.cellstudio.domain.entity.Image;

/**
 * Created by coyan on 07/12/2018.
 */

public class ImageDataEntityMapper {
    public static Image create (ImageDataModel image) {
        return new Image(image.getPoster(), image.getFanart(), image.getBanner());
    }
}
