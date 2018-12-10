package com.app.cellstudio.domain.entity;

/**
 * Created by coyan on 07/12/2018.
 */

public class Image {
    private String poster;
    private String fanart;
    private String banner;

    public Image(String poster, String fanart, String banner) {
        this.poster = poster;
        this.fanart = fanart;
        this.banner = banner;
    }

    public String getPoster() {
        return poster;
    }

    public String getFanart() {
        return fanart;
    }

    public String getBanner() {
        return banner;
    }
}
