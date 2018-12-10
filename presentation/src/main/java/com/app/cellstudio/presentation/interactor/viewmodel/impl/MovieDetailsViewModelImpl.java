package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MovieDetailsViewModel;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by coyan on 04/12/2018.
 */

public class MovieDetailsViewModelImpl extends BaseViewModel implements MovieDetailsViewModel {
    private PublishSubject<Boolean> isWatchTrailerButtonClicked = PublishSubject.create();

    private ObservableField<String> moreText = new ObservableField<>("More");
    private ObservableBoolean isExpand = new ObservableBoolean(false);
    private ObservableInt synopsisSize = new ObservableInt(5);

    public MovieDetailsViewModelImpl(BaseSchedulerProvider scheduler) {
        super(scheduler);
    }

    @Override
    public void onWatchTrailerButtonClicked() {
        isWatchTrailerButtonClicked.onNext(true);
    }

    @Override
    public void onMoreButtonClicked(View view) {
        if (isExpand.get()) {
            moreText.set(view.getResources().getString(R.string.text_MORE));
            synopsisSize.set(view.getResources().getInteger(R.integer.synopsis_max_lines));
        } else {
            moreText.set(view.getResources().getString(R.string.text_LESS));
            synopsisSize.set(Integer.MAX_VALUE);
        }
        isExpand.set(!isExpand.get());
    }

    @Override
    public PublishSubject<Boolean> getWatchTrailerButtonClicked() {
        return isWatchTrailerButtonClicked;
    }

    @Override
    public ObservableInt getSynopsisSize() {
        return synopsisSize;
    }

    @Override
    public ObservableField<String> getMoreText() {
        return moreText;
    }

    @Override
    public ObservableBoolean getIsExpand() {
        return isExpand;
    }
}
