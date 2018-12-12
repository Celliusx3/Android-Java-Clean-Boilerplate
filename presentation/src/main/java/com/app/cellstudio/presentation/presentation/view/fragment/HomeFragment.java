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
import com.app.cellstudio.presentation.presentation.view.adapter.MovieListAdapter;
import com.app.cellstudio.presentation.presentation.view.component.OnEndlessScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

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
    private int currentMoviePageInIndex;
    private List<String> moviePages;

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

        homeViewModel.getMoviePages()
            .compose(bindToLifecycle())
            .subscribeOn(getIoScheduler())
            .observeOn(getUiScheduler())
            .subscribe(moviePages-> {
                this.moviePages = moviePages;
                this.currentMoviePageInIndex = 0;

                this.getSpecificMoviePage(moviePages.get(currentMoviePageInIndex))
                        .subscribe(this::setupMoviesList);
            });
    }

    @Override
    public void onResume() {
        super.onResume();

        homeViewModel.isLoading()
            .compose(bindToLifecycle())
            .subscribeOn(getIoScheduler())
            .observeOn(getUiScheduler())
            .subscribe(isLoading-> {
                if (this.movieListAdapter != null) {
                    this.movieListAdapter.setLoading(isLoading);
                }
            });

        subscribeSelectedMovie();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setupMoviesList(List<MoviePresentationModel> movies) {
        int spanCount = 2;
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(movieListAdapter.getItemViewType(position)){
                    case MovieListAdapter.VIEW_TYPE_MOVIE:
                        return 1;
                    case MovieListAdapter.VIEW_TYPE_LOADING:
                        return spanCount; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });
        rvHome.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(movies);
        rvHome.setAdapter(movieListAdapter);
        rvHome.setNestedScrollingEnabled(false);
        rvHome.addOnScrollListener(new OnEndlessScrollListener() {
            @Override
            public void onLoadMore() {
                currentMoviePageInIndex += 1;
                if (currentMoviePageInIndex < moviePages.size()) {
                    getSpecificMoviePage(moviePages.get(currentMoviePageInIndex))
                        .subscribe(moviePage -> movieListAdapter.updateData(moviePage));
                }
            }
        });
        subscribeSelectedMovie();

    }

    private void subscribeSelectedMovie() {
        if (movieListAdapter == null)
            return;

        movieListAdapter.getSelectedMovie()
                .compose(bindToLifecycle())
                .observeOn(getUiScheduler())
                .subscribe(selectedMovie -> navigator.navigateToMovieDetails(getContext(), selectedMovie),Throwable::printStackTrace);
    }

    private Observable<List<MoviePresentationModel>> getSpecificMoviePage(String path) {
        return homeViewModel.getMoviePage(path)
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler());
    }
}