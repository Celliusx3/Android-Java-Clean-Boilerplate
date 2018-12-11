package com.app.cellstudio.presentation.interactor.viewmodel;

import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 11/12/2018.
 */

public interface HomeViewModel extends ViewModel {
    Observable<List<String>> getMoviePages();
    Observable<List<MoviePresentationModel>> getMoviePage();
}
