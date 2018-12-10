package com.app.cellstudio.presentation.interactor.mapper;

import com.app.cellstudio.domain.entity.Image;
import com.app.cellstudio.presentation.interactor.model.ImagePresentationModel;

/**
 * Created by coyan on 06/12/2018.
 */

public class ImagePresentationModelMapper {

    public static ImagePresentationModel create (Image image) {
        return new ImagePresentationModel(image.getPoster(), image.getFanart(), image.getBanner());
    }
}
