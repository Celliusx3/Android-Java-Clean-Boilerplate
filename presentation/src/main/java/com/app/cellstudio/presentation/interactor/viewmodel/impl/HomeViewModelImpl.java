package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.presentation.interactor.mapper.MoviePresentationModelMapper;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.HomeViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 11/12/2018.
 */

public class HomeViewModelImpl extends BaseViewModel implements HomeViewModel {
    protected final MovieInteractor movieInteractor;

    public HomeViewModelImpl(MovieInteractor movieInteractor, BaseSchedulerProvider scheduler) {
        super(scheduler);
        this.movieInteractor = movieInteractor;
    }


    @Override
    public Observable<List<String>> getMoviePages() {
        return movieInteractor.getPages();
    }

    @Override
    public Observable<List<MoviePresentationModel>> getMoviePage() {
        return movieInteractor.getPage(1)
                .flatMap(Observable::fromIterable)
                .map(MoviePresentationModelMapper::create)
                .toList()
                .toObservable();
    }
}
