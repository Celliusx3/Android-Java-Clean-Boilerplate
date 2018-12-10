package com.app.cellstudio.presentation.interactor.viewmodel;

import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;

/**
 * Created by coyan on 30/11/2018.
 */

public abstract class BaseViewModel implements ViewModel {

    protected final BaseSchedulerProvider scheduler;

    public BaseViewModel(BaseSchedulerProvider scheduler) {
        this.scheduler = scheduler;
    }
}
