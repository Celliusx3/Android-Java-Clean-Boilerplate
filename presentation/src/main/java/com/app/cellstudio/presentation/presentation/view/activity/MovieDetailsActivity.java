package com.app.cellstudio.presentation.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.R;
import com.app.cellstudio.presentation.databinding.ActivityMovieDetailsBinding;
import com.app.cellstudio.presentation.di.modules.MovieDetailsModule;
import com.app.cellstudio.presentation.interactor.model.MoviePresentationModel;
import com.app.cellstudio.presentation.interactor.viewmodel.MovieDetailsViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.ViewModel;
import com.app.cellstudio.presentation.presentation.navigation.Navigator;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MovieDetailsActivity extends BaseActivity {

    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    @Inject
    MovieDetailsViewModel movieDetailsViewModel;

    @BindView(R.id.rl_details_main)
    RelativeLayout rlMovieDetails;

    private ActivityMovieDetailsBinding binding;
    private MoviePresentationModel movie;

    public static Intent getCallingIntent(Context context, MoviePresentationModel movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_details;
    }

    @Override
    protected List<ViewModel> getViewModels() {
        return Collections.singletonList(movieDetailsViewModel);
    }

    @Override
    protected View getRootView() {
        return rlMovieDetails;
    }

    protected void onInject() {
        BaseApplication.getInstance()
                .getApplicationComponent()
                .plus(new MovieDetailsModule(this))
                .inject(this);

        movieDetailsViewModel.getWatchTrailerButtonClicked()
                .compose(bindToLifecycle())
                .subscribeOn(getIoScheduler())
                .observeOn(getUiScheduler())
                .subscribe(clicked -> {
                    Navigator.navigateToYoutube(this, movie.getTrailer());
                });
    }

    @Override
    protected void onBindData(View view, Bundle savedInstanceState) {
        super.onBindData(view, savedInstanceState);

        binding = DataBindingUtil.bind(view);
        binding.setModel(movie);
        binding.setViewModel(movieDetailsViewModel);
    }

    @Override
    protected void onGetInputData(Bundle savedInstanceState) {
        super.onGetInputData(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getParcelableExtra(EXTRA_MOVIE);
        }
    }
}
