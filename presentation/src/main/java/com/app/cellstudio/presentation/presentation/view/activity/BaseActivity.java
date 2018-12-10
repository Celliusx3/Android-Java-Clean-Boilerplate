package com.app.cellstudio.presentation.presentation.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.presentation.navigation.Navigator;
import com.app.cellstudio.presentation.interactor.viewmodel.ViewModel;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Scheduler;

/**
 * Created by coyan on 30/11/2018.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private static final long DOUBLE_BACK_TO_EXIT_DURATION = 2000;

    @Inject
    Navigator navigator;

    @Inject
    BaseSchedulerProvider scheduler;

//    @Nullable
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    private Unbinder unbinder;

//    protected AlertDialog alertDialog;

    private long backButtonLastPressedElapsedTime;

    protected abstract @LayoutRes
    int getLayoutResource();

    protected abstract List<ViewModel> getViewModels();

    protected abstract View getRootView();

    public Scheduler getUiScheduler() {
        return scheduler.ui();
    }

    public Scheduler getIoScheduler() {
        return scheduler.io();
    }

    public Navigator getNavigator() {
        return navigator;
    }

    @Nullable
    public Toolbar getToolbar() {
//        return toolbar;
        return null;
    }

    protected String getToolbarTitle() {
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onSetContentView();
        onGetInputData(savedInstanceState);
        onInject();
        onBindView();

//        if (getViewModels() != null) {
//            for (ViewModel viewModel : getViewModels()) {
//                viewModel.getInput().onCreateView();
//
//                ViewModel.Output output = viewModel.getOutput();
//
//                output.getShowToastMessage()
//                        .compose(bindToLifecycle())
//                        .observeOn(getUiScheduler())
//                        .subscribe(this::showToastMessage);
//            }
//
//        }

        onBindData(getRootView(), savedInstanceState);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected void onSetContentView() {
        if (getLayoutResource() != 0) {
            setContentView(getLayoutResource());
        }
    }

    protected void onBindView() {
        unbinder = ButterKnife.bind(this);
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            if (getToolbarTitle() != null) {
                toolbar.setTitle(getToolbarTitle());
            }
            toolbar.setOnClickListener(v -> onSetToolbarClick());
            setSupportActionBar(toolbar);
        }
    }

    protected void onInject() {
    }

    protected void onBindData(@Nullable View view, Bundle savedInstanceState) {
        if (savedInstanceState != null)
            return;
    }

    protected void onGetInputData(Bundle savedInstanceState) {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (getViewModels() != null) {
//            for (ViewModel viewModel : getViewModels()) {
//                viewModel.getInput().onAttachView();
//            }
//        }
//        setViewModelViewEvent();
    }

//    protected void setViewModelViewEvent() {
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
//
//        if (getViewModels() != null) {
//            for (ViewModel viewModel : getViewModels()) {
//                viewModel.getInput().onDetachView();
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void startActivity(Intent intent) {
        try {
            super.startActivity(intent);
            startActivityTransitions();
        } catch (NullPointerException npe) {

        } catch (ActivityNotFoundException anfe) {
            anfe.printStackTrace();
        }
    }

    protected void startActivityTransitions() {
        getNavigator().showPendingTransitions(this);
    }

    @Override
    public void finish() {
        super.finish();
        finishActivityTransitions();
    }

    protected void finishActivityTransitions() {
        if (getNavigator() != null)
            getNavigator().showPendingTransitions(this);
    }

    protected void onSetToolbarClick(){
    }

    protected boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }

}
