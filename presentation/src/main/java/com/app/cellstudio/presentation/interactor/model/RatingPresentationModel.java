package com.app.cellstudio.presentation.interactor.model;

import android.os.Parcel;
import android.os.Parcelable;

import paperparcel.PaperParcel;

/**
 * Created by coyan on 06/12/2018.
 */

@PaperParcel
public class RatingPresentationModel implements Parcelable {
    float percentage;
    int watching;
    int votes;
    int loved;
    int hated;

    public static final Creator<RatingPresentationModel> CREATOR
            = PaperParcelRatingPresentationModel.CREATOR;

    public RatingPresentationModel(float percentage, int watching, int votes, int loved, int hated) {
        this.percentage = percentage;
        this.watching = watching;
        this.votes = votes;
        this.loved = loved;
        this.hated = hated;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        PaperParcelRatingPresentationModel.writeToParcel(this, parcel, i); // (4)
    }

    public float getPercentage() {
        return percentage;
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
}