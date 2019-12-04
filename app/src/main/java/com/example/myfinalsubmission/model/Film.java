package com.example.myfinalsubmission.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Film implements Parcelable {
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
    private String title, description, releaseDate, country, genre, userScore, director, runtime, budget, revenue, photo;

    public Film() {
    }

    public Film(String title, String overview, String vote_average, String poster_path) {
        this.title = title;
        this.description = overview;
        this.userScore = vote_average;
        this.photo = poster_path;
    }

    public Film(Parcel in) {
        title = in.readString();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        dest.writeString(title);
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
