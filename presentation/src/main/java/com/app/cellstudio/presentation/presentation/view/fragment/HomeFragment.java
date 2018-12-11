package com.app.cellstudio.presentation.presentation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.di.modules.HomeModule;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.HomeViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.ViewModel;
import com.app.cellstudio.presentation.presentation.view.adapter.MovieListAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    BaseSchedulerProvider scheduler;

    @Inject
    HomeViewModel homeViewModel;

    @BindView(R.id.rl_home)
    RelativeLayout rlHome;

    @BindView(R.id.rv_home_item)
    RecyclerView rvHome;

    private MovieListAdapter movieListAdapter;

    public static HomeFragment newInstance() {
        final Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(homeViewModel);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
    }

    @Override
    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new HomeModule(getContext()))
                .inject(this);
    }

    @Override
    protected void onBindData(View view) {
        super.onBindData(view);

        homeViewModel.getMoviePage()
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(this::setupMoviesList);
    }

    @Override
    public void onResume() {
        super.onResume();
        subscribeSelectedMovie();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setupMoviesList(List<MoviePresentationModel> movies) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvHome.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(movies);
        rvHome.setAdapter(movieListAdapter);
        rvHome.setNestedScrollingEnabled(false);
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
                    navigator.navigateToMovieDetails(getContext(), selectedMovie);
                },Throwable::printStackTrace);
    }
}