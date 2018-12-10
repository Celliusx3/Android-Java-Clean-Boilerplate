package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MovieDetailsViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 04/12/2018.
 */

public class MovieDetailsViewModelImpl extends BaseViewModel implements MovieDetailsViewModel {
    private PublishSubject<Boolean> isWatchTrailerButtonClicked = PublishSubject.create();

    public MovieDetailsViewModelImpl(BaseSchedulerProvider scheduler) {
        super(scheduler);
    }

    @Override
    public void onWatchTrailerButtonClicked() {
        isWatchTrailerButtonClicked.onNext(true);
    }

    @Override
    public PublishSubject<Boolean> getWatchTrailerButtonClicked() {
        return isWatchTrailerButtonClicked;
    }
}
