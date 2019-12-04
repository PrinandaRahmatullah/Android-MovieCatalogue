package com.example.mine.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tv extends Movies implements Parcelable {
    private String name;

    public Tv() {}
    public Tv(String title,String overview,String vote_average,String poster_path){
        this.name= title;
        this.description = overview;
        this.userScore = vote_average;
        this.photo = poster_path;
    }


    private Tv(Parcel in) {
        name = in.readString();
        description = in.readString();
        userScore =in.readString();
        photo =in.readString();
    }

    public static final Creator<Tv> CREATOR = new Creator<Tv>() {
        @Override
        public Tv createFromParcel(Parcel in) {
            return new Tv(in);
        }

        @Override
        public Tv[] newArray(int size) {
            return new Tv[size];
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(userScore);
        dest.writeString(photo);
    }
}

