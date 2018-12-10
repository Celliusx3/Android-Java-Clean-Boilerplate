package com.app.cellstudio.data.repository;

import com.app.cellstudio.data.api.ApiService;
import com.app.cellstudio.data.http.HttpClient;
import com.app.cellstudio.data.mapper.MovieDataEntityMapper;
import com.app.cellstudio.domain.entity.Movie;
import com.app.cellstudio.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by coyan on 30/11/2018.
 */

public class MovieRepositoryImpl implements MovieRepository {

    private final HttpClient httpClient;

    public MovieRepositoryImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private ApiService getApiService() { return httpClient.getApiService();}

    @Override
    public Observable<List<String>> getMoviePages() {
        return getApiService().getMoviePages();
    }

    @Override
    public Observable<List<Movie>> getMoviePage(int pageId) {
        return getApiService().getMoviePage(pageId)
                .flatMap(Observable::fromIterable)
                .map(MovieDataEntityMapper::create)
                .toList()
                .toObservable();
    }
}