package com.app.cellstudio.presentation.interactor.model;

import android.os.Parcel;
import android.os.Parcelable;

import paperparcel.PaperParcel;

/**
 * Created by coyan on 06/12/2018.
 */

@PaperParcel
public class ImagePresentationModel implements Parcelable {
    String poster;
    String fanart;
    String banner;

    public static final Creator<ImagePresentationModel> CREATOR
            = PaperParcelImagePresentationModel.CREATOR;

    public ImagePresentationModel(String poster, String fanart, String banner) {
        this.poster = poster;
        this.fanart = fanart;
        this.banner = banner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        PaperParcelImagePresentationModel.writeToParcel(this, parcel, i); // (4)
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
