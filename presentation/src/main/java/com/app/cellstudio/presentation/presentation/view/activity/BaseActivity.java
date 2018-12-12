package com.app.cellstudio.presentation.presentation.view.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.presentation.navigation.Navigator;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Scheduler;

/**
 * Created by coyan on 30/11/2018.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Inject
    Navigator navigator;

    @Inject
    BaseSchedulerProvider scheduler;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;

    protected abstract @LayoutRes
    int getLayoutResource();

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
        return toolbar;
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
    }

    @Override
    protected void onPause() {
        super.onPause();
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

    protected void setToolbarTitle(String title) {
        Toolbar toolbar = getToolbar();
        if (toolbar != null && !TextUtils.isEmpty(title)) {
            toolbar.setTitle(title);
        }
    }

}
