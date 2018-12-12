package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.presentation.interactor.mapper.MoviePresentationModelMapper;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.HomeViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 11/12/2018.
 */

public class HomeViewModelImpl extends BaseViewModel implements HomeViewModel {
    private final MovieInteractor movieInteractor;
    private PublishSubject<Boolean> isLoading = PublishSubject.create();

    public HomeViewModelImpl(MovieInteractor movieInteractor, BaseSchedulerProvider scheduler) {
        super(scheduler);
        this.movieInteractor = movieInteractor;
    }


    @Override
    public Observable<List<String>> getMoviePages() {
        isLoading.onNext(true);
        return movieInteractor.getMoviePages()
                .doFinally(() -> isLoading.onNext(false));
    }

    @Override
    public Observable<List<MoviePresentationModel>> getMoviePage(String path) {
        isLoading.onNext(true);
        return movieInteractor.getMoviePage(path)
                .flatMap(Observable::fromIterable)
                .map(MoviePresentationModelMapper::create)
                .toList()
                .toObservable()
                .doFinally(() -> isLoading.onNext(false));
    }

    @Override
    public Observable<Boolean> isLoading() {
        return isLoading;
    }
}
