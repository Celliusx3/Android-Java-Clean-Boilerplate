package com.app.cellstudio.presentation.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.presentation.navigation.Navigator;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by coyan on 11/12/2018.
 */

public abstract class BaseFragment extends RxFragment {

    @Inject
    Navigator navigator;

    @Inject
    BaseSchedulerProvider scheduler;

    private Unbinder unbinder;

    private BehaviorSubject<Boolean> currentUserVisibleHint = BehaviorSubject.createDefault(this.getUserVisibleHint());

    protected abstract @LayoutRes
    int getLayoutResource();

    public Observable<Boolean> getCurrentUserVisibleHint() {
        return currentUserVisibleHint;
    }

    protected Scheduler getUiScheduler() {
        return scheduler.ui();
    }

    protected Scheduler getIoScheduler() {
        return scheduler.io();
    }

    protected Navigator getNavigator() {
        return navigator;
    }

    public String getTitle() {
        return "";
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onGetInputData();
        onInject();
        onBindView(view);
        onBindData(view);
    }

    protected void onBindView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    protected void onInject() {
    }

    protected void onBindData(View view) {
    }

    protected void onGetInputData() {
    }

    @Override
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        currentUserVisibleHint.onNext(isVisibleToUser);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }
}

