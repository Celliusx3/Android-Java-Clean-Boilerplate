package com.app.cellstudio.data.mapper;

import com.app.cellstudio.data.entity.RatingDataModel;
import com.app.cellstudio.domain.entity.Rating;

/**
 * Created by coyan on 07/12/2018.
 */

public class RatingDataEntityMapper {
    public static Rating create (RatingDataModel ratingDataModel) {
        return new Rating(ratingDataModel.getPercentage(), ratingDataModel.getWatching(), ratingDataModel.getVotes(),
                ratingDataModel.getLoved(), ratingDataModel.getHated());
    }
}
