package com.app.cellstudio.domain.interactor.impl;

import com.app.cellstudio.domain.entity.Movie;
import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public class MovieInteractorImpl implements MovieInteractor {

    private final MovieRepository movieRepository;

    public MovieInteractorImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<List<String>> getMoviePages() {
        return movieRepository.getMoviePages();
    }

    @Override
    public Observable<List<Movie>> getMoviePage(String path) {
        return movieRepository.getMoviePage(path);
    }
}
