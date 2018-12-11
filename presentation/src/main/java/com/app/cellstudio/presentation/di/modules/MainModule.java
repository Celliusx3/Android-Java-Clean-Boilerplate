package com.app.cellstudio.presentation.di.modules;

import android.content.Context;

import com.app.cellstudio.domain.interactor.MainInteractor;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.viewmodel.MainViewModel;
import com.app.cellstudio.presentation.interactor.viewmodel.impl.MainViewModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 29/11/2018.
 */

@Module
public class MainModule {

    private Context mContext;
    public MainModule(Context context) {
        mContext = context;
    }

    @Provides
    MainViewModel provideMainViewModel(MainInteractor mainInteractor, BaseSchedulerProvider provider) {
        return new MainViewModelImpl(mainInteractor, provider);
    }
}