package com.app.cellstudio.data.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by coyan on 07/12/2018.
 */

public class MovieDataModel {
    private String _id;
    private String imdb_id;
    private String title;
    private String year;
    private String synopsis;
    private String runtime;
    private int released;
    private String trailer;
    private String certification;
    private List<String> genres;
    private HashMap<String, Object> torrents;
    private ImageDataModel images;
    private RatingDataModel rating;

    public String getId() {
        return _id;
    }

    public String getImdbId() { return imdb_id; }

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

    public ImageDataModel getImages() {
        return images;
    }

    public RatingDataModel getRating() {
        return rating;
    }
}
