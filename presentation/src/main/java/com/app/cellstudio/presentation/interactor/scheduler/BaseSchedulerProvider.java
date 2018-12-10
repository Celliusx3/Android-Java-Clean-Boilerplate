package com.app.cellstudio.presentation.interactor.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Created by coyan on 29/11/2018.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
