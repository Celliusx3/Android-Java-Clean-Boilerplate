package com.app.cellstudio.presentation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.di.modules.MainModule;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.ViewModel;
import com.app.cellstudio.presentation.presentation.view.adapter.MovieListAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainViewModel mainViewModel;

    @BindView(R.id.rl_main)
    RelativeLayout rlMain;

    @BindView(R.id.rv_main_item)
    RecyclerView rvMain;

    private MovieListAdapter movieListAdapter;

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(mainViewModel);
    }

    @Override
    protected View getRootView() {
        return rlMain;
    }

    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new MainModule(this))
                .inject(this);
    }

    @Override
    protected void onBindData(View view, Bundle savedInstanceState) {
        super.onBindData(view, savedInstanceState);

        mainViewModel.getMoviePages()
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(models -> {
                    for (String model: models) {
                        Log.d(TAG, model);
                    }
                });

        mainViewModel.getMoviePage()
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(this::setupMoviesList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscribeSelectedMovie();
    }

    private void setupMoviesList(List<MoviePresentationModel> movies) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvMain.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(movies);
        rvMain.setAdapter(movieListAdapter);
        rvMain.setNestedScrollingEnabled(false);
        subscribeSelectedMovie();
    }

    private void subscribeSelectedMovie() {
        if (movieListAdapter == null)
            return;

        // Switch Language
        movieListAdapter.getSelectedMovie()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(selectedMovie -> {
                    navigator.navigateToMovieDetails(this, selectedMovie);
                },Throwable::printStackTrace);
    }

}
