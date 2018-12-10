package com.app.cellstudio.presentation.interactor.viewmodel;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 04/12/2018.
 */

public interface MovieDetailsViewModel extends ViewModel {

    void onWatchTrailerButtonClicked();
    PublishSubject<Boolean> getWatchTrailerButtonClicked();
}
