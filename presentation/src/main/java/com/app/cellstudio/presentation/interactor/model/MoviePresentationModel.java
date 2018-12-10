package com.app.cellstudio.presentation.interactor.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;

import paperparcel.PaperParcel;

/**
 * Created by coyan on 06/12/2018.
 */

@PaperParcel
public class MoviePresentationModel implements Parcelable {

    String id;
    String title;
    String year;
    String synopsis;
    String runtime;
    int released;
    String trailer;
    String certification;
    List<String> genres;
    HashMap<String, Object> torrents;
    ImagePresentationModel images;
    RatingPresentationModel rating;
    String year_runtime_genres;

    public static final Creator<MoviePresentationModel> CREATOR
            = PaperParcelMoviePresentationModel.CREATOR;

    public MoviePresentationModel(String id, String title, String year, String synopsis, String runtime, int released,
                                  String trailer, String certification, List<String> genres, HashMap<String, Object> torrents,
                                  ImagePresentationModel images, RatingPresentationModel rating, String year_runtime_genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.synopsis = synopsis;
        this.runtime = runtime;
        this.released = released;
        this.trailer = trailer;
        this.certification = certification;
        this.genres = genres;
        this.torrents = torrents;
        this.images = images;
        this.rating = rating;
        this.year_runtime_genres = year_runtime_genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        PaperParcelMoviePresentationModel.writeToParcel(this, parcel, i); // (4)
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getRuntime() {
        return runtime;
    }

    public int getReleased() {
        return released;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getCertification() {
        return certification;
    }

    public List<String> getGenres() {
        return genres;
    }

    public HashMap<String, Object> getTorrents() {
        return torrents;
    }

    public ImagePresentationModel getImages() {
        return images;
    }

    public RatingPresentationModel getRating() {
        return rating;
    }

    public String getYearRuntimeGenres() {return year_runtime_genres;}
}