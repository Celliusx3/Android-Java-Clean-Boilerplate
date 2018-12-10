package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.presentation.interactor.mapper.MoviePresentationModelMapper;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MainViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public class MainViewModelImpl extends BaseViewModel implements MainViewModel {
    protected final MovieInteractor movieInteractor;

    public MainViewModelImpl(MovieInteractor movieInteractor, BaseSchedulerProvider scheduler) {
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
