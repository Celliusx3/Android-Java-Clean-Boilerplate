package com.app.cellstudio.presentation.di.modules;

import android.content.Context;

import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.MovieDetailsViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.impl.MovieDetailsViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 04/12/2018.
 */

@Module
public class MovieDetailsModule {
    private Context mContext;
    public MovieDetailsModule(Context context) {
        mContext = context;
    }

    @Provides
    MovieDetailsViewModel provideMovieDetailsViewModel(BaseSchedulerProvider provider) {
        return new MovieDetailsViewModelImpl(provider);
    }
}
