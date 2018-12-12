package com.app.cellstudio.data.api;

import com.app.cellstudio.data.entity.MovieDataModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by coyan on 29/11/2018.
 */

public interface ApiService {
    @GET(ApiRoutes.MOVIE)
    Observable<List<String>> getMoviePages();

    @GET("{path}")
    Observable<List<MovieDataModel>> getMoviePage(@Path("path")String path);
}
