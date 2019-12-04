package com.example.myfinalsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Tv implements Parcelable {
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
    private String name, description, releaseDate, country, genre, userScore, director, runtime, budget, revenue, photo;

    public Tv() {
    }

    public Tv(String title, String overview, String vote_average, String poster_path) {
        this.name = title;
        this.description = overview;
        this.userScore = vote_average;
        this.photo = poster_path;
    }

    protected Tv(Parcel in) {
        name = in.readString();
        description = in.readString();
        releaseDate = in.readString();
        country = in.readString();
        genre = in.readString();
        userScore = in.readString();
        director = in.readString();
        runtime = in.readString();
        budget = in.readString();
        revenue = in.readString();
        photo = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(releaseDate);
        dest.writeString(country);
        dest.writeString(genre);
        dest.writeString(userScore);
        dest.writeString(director);
        dest.writeString(runtime);
        dest.writeString(budget);
        dest.writeString(revenue);
        dest.writeString(photo);
    }
}

