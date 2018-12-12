package com.app.cellstudio.domain.interactor;

import com.app.cellstudio.domain.entity.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public interface MovieInteractor {
    Observable<List<String>> getMoviePages();
    Observable<List<Movie>> getMoviePage(String path);
}
