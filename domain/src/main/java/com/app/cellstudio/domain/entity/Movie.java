package com.app.cellstudio.domain.entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by coyan on 07/12/2018.
 */

public class Movie {
    private String id;
    private String title;
    private String year;
    private String synopsis;
    private String runtime;
    private int released;
    private String trailer;
    private String certification;
    private List<String> genres;
    private HashMap<String, Object> torrents;
    private Image images;
    private Rating rating;

    public Movie(String id, String title, String year, String synopsis, String runtime, int released,
                 String trailer, String certification, List<String> genres, HashMap<String, Object> torrents,
                 Image images, Rating rating) {
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
    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getYear(){
        return year;
    }

    public String getSynopsis(){
        return synopsis;
    }

    public String getRuntime(){
        return runtime;
    }

    public int getReleased(){
        return released;
    }

    public String getTrailer(){
        return trailer;
    }

    public String getCertification(){
        return certification;
    }

    public List<String> getGenres(){
        return genres;
    }

    public HashMap<String, Object> getTorrents(){
        return torrents;
    }

    public Image getImages(){
        return images;
    }

    public Rating getRating(){
        return rating;
    }

    public String getYearRuntimeGenres() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.year).append(" • ").append(this.runtime).append(" mins");
        if(!genres.isEmpty()) {
            sb.append(" • ").append(genres.get(0));
        }

        return sb.toString();
    }
}
