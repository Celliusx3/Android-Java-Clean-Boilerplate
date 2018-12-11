package com.app.cellstudio.presentation.di.modules;

import com.app.cellstudio.domain.interactor.MainInteractor;
import com.app.cellstudio.domain.interactor.MovieInteractor;
import com.app.cellstudio.domain.interactor.impl.MainInteractorImpl;
import com.app.cellstudio.domain.interactor.impl.MovieInteractorImpl;
import com.app.cellstudio.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 30/11/2018.
 */


@Module
public class InteractorModule {
    @Singleton
    @Provides
    MovieInteractor provideMovieInteractor(MovieRepository movieRepository) {
        return new MovieInteractorImpl(movieRepository);
    }

    @Singleton
    @Provides
    MainInteractor provideMainInteraactor() {
        return new MainInteractorImpl();
    }
}
