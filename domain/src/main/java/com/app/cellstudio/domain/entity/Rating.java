package com.app.cellstudio.domain.entity;

/**
 * Created by coyan on 07/12/2018.
 */

public class Rating {
    private int percentage;
    private int watching;
    private int votes;
    private int loved;
    private int hated;

    public Rating(int percentage, int watching, int votes, int loved, int hated) {
        this.percentage = percentage;
        this.watching = watching;
        this.votes = votes;
        this.loved = loved;
        this.hated = hated;
    }

    public int getPercentage() {
        return percentage ;
    }

    public int getWatching() {
        return watching;
    }

    public int getVotes() {
        return votes;
    }

    public int getLoved() {
        return loved;
    }

    public int getHated() {
        return hated;
    }

    public float getPercentageForRating () {
        return percentage / 20.0f;
    }
}
