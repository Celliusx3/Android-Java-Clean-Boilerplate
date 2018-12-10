package com.app.cellstudio.presentation.interactor.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by coyan on 29/11/2018.
 */

public class SchedulerProvider implements BaseSchedulerProvider {
    private static BaseSchedulerProvider sInstance;

    public static BaseSchedulerProvider getInstance() {
        if(sInstance == null) {
            sInstance = new SchedulerProvider();
        }
        return sInstance;
    }

    private SchedulerProvider() {

    }

    @android.support.annotation.NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @android.support.annotation.NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @android.support.annotation.NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
