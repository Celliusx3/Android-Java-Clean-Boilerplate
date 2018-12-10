package com.app.cellstudio.presentation.interactor.mapper;

import com.app.cellstudio.domain.entity.Rating;
import com.app.cellstudio.presentation.interactor.model.RatingPresentationModel;

/**
 * Created by coyan on 06/12/2018.
 */

public class RatingPresentationModelMapper {
    public static RatingPresentationModel create (Rating rating) {
        return new RatingPresentationModel(rating.getPercentageForRating(), rating.getWatching(), rating.getVotes(),
                rating.getLoved(), rating.getHated());
    }
}