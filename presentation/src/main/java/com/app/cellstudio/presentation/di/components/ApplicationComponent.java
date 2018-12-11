package com.app.cellstudio.presentation.di.components;

import android.content.Context;

import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.di.modules.ApplicationModule;
import com.app.cellstudio.presentation.di.modules.HomeModule;
import com.app.cellstudio.presentation.di.modules.InteractorModule;
import com.app.cellstudio.presentation.di.modules.MainModule;
import com.app.cellstudio.presentation.di.modules.MovieDetailsModule;
import com.app.cellstudio.presentation.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by coyan on 29/11/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, InteractorModule.class, RepositoryModule.class, })
public interface ApplicationComponent {
    BaseApplication getApplication();
    Context getApplicationContext();
    void inject(BaseApplication baseApplication);
    MainComponent plus(MainModule mainModule);
    HomeComponent plus(HomeModule homeModule);
    MovieDetailsComponent plus(MovieDetailsModule movieDetailsModule);
}
