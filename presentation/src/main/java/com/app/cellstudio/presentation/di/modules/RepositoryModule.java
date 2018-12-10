package com.app.cellstudio.presentation.di.modules;

import com.app.cellstudio.data.http.HttpClient;
import com.app.cellstudio.data.repository.MovieRepositoryImpl;
import com.app.cellstudio.domain.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 30/11/2018.
 */

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    MovieRepository provideMovieRepository(HttpClient httpClient) {
        return new MovieRepositoryImpl(httpClient);
    }
}