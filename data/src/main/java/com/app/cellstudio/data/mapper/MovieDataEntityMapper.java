package com.app.cellstudio.data.mapper;

import com.app.cellstudio.data.entity.MovieDataModel;
import com.app.cellstudio.domain.entity.Image;
import com.app.cellstudio.domain.entity.Movie;
import com.app.cellstudio.domain.entity.Rating;

/**
 * Created by coyan on 07/12/2018.
 */

public class MovieDataEntityMapper {
    public static Movie create (MovieDataModel movieDataModel) {
        Image image = ImageDataEntityMapper.create(movieDataModel.getImages());
        Rating rating = RatingDataEntityMapper.create(movieDataModel.getRating());

        return new Movie(movieDataModel.getId(), movieDataModel.getTitle(), movieDataModel.getYear(), movieDataModel.getSynopsis(),
                movieDataModel.getRuntime(), movieDataModel.getReleased(), movieDataModel.getTrailer(), movieDataModel.getCertification(), movieDataModel.getGenres(),
                movieDataModel.getTorrents(), image, rating);
    }
}
