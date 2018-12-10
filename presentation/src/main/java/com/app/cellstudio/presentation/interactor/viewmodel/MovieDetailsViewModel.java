package com.app.cellstudio.presentation.interactor.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 04/12/2018.
 */

public interface MovieDetailsViewModel extends ViewModel {

    void onWatchTrailerButtonClicked();
    void onMoreButtonClicked(View view);
    PublishSubject<Boolean> getWatchTrailerButtonClicked();
    ObservableInt getSynopsisSize();
    ObservableField<String> getMoreText();
    ObservableBoolean getIsExpand();

}
