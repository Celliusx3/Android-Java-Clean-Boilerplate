package com.app.cellstudio.presentation.interactor.viewmodel.impl;

import com.app.cellstudio.domain.entity.Page;
import com.app.cellstudio.domain.interactor.MainInteractor;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.BaseViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MainViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public class MainViewModelImpl extends BaseViewModel implements MainViewModel {
    private final MainInteractor mainInteractor;

    public MainViewModelImpl(MainInteractor mainInteractor, BaseSchedulerProvider scheduler) {
        super(scheduler);
        this.mainInteractor = mainInteractor;
    }

    @Override
    public Observable<List<Page>> getFragmentPages() {
        return this.mainInteractor.getFragmentPages();
    }
}
