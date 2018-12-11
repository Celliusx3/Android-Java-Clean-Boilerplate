package com.app.cellstudio.domain.entity;

/**
 * Created by coyan on 11/12/2018.
 */

public class Page {
    public static final Page HomePage = new Page("Home", 1000);
    public static final Page SettingsPage = new Page ("Settings", 1001);

    private String title;
    private int pageId;

    public Page (String title, int pageId) {
        this.title = title;
        this.pageId = pageId;
    }

    public String getTitle() {
        return title;
    }

    public int getPageId() {
        return pageId;
    }
}
