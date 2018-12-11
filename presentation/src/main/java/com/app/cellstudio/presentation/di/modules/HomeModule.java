package com.app.cellstudio.presentation.di.modules;

import android.content.Context;

import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.HomeViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.impl.HomeViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 11/12/2018.
 */

@Module
public class HomeModule {

    private Context mContext;

    public HomeModule(Context context) {
        mContext = context;
    }

    @Provides
    HomeViewModel provideHomeViewModel(MovieInteractor movieInteractor,
                                       BaseSchedulerProvider provider) {
        return new HomeViewModelImpl(movieInteractor, provider);
    }
}
