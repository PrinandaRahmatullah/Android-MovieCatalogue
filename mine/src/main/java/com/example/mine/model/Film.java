package com.example.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film extends Movies implements Parcelable{
    private String title;

    public Film() {
    }

    public Film(String title,String overview,String vote_average,String poster_path){
        this.title = title;
        this.description = overview;
        this.userScore = vote_average;
        this.photo = poster_path;
    }

    private Film(Parcel in) {
        title = in.readString();
        description = in.readString();
        userScore = in.readString();
        photo = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel in) {
            return new Film(in);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(userScore);
        dest.writeString(photo);
    }
}
