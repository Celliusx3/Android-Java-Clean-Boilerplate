package com.app.cellstudio.presentation.interactor.mapper;

import com.app.cellstudio.domain.entity.Movie;
import com.app.cellstudio.presentation.interactor.model.ImagePresentationModel;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.model.RatingPresentationModel;

/**
 * Created by coyan on 06/12/2018.
 */


public class MoviePresentationModelMapper {

    public static MoviePresentationModel create (Movie movie) {
        ImagePresentationModel imagePresentationModel = ImagePresentationModelMapper.create(movie.getImages());
        RatingPresentationModel ratingPresentationModel = RatingPresentationModelMapper.create(movie.getRating());

        return new MoviePresentationModel(movie.getId(), movie.getTitle(), movie.getYear(), movie.getSynopsis(),
                movie.getRuntime(), movie.getReleased(), movie.getTrailer(), movie.getCertification(), movie.getGenres(),
                movie.getTorrents(), imagePresentationModel, ratingPresentationModel, movie.getYearRuntimeGenres());
    }
}