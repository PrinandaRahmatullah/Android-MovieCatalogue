package com.example.mine.viewmodel;

import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mine.database.DatabaseContract;
import com.example.mine.database.FilmHelper;
import com.example.mine.model.Film;

import java.util.ArrayList;

public class FilmViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Film>> listFilmMut = new MutableLiveData<>();

    public void setFilmFav(Context context) {

        ArrayList<Film> filmArrayList = new ArrayList<>();
        FilmHelper filmHelper = new FilmHelper(context);
        filmHelper.open();

        Cursor cursor = filmHelper.queryAll();

        if (cursor != null) {

            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.TITLE_FILM));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.OVERVIEW_FILM));
                String vote_average = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.VOTE_AVERAGE_FILM));
                String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.POSTER_PATH_FILM));
                filmArrayList.add(new Film(title, overview, vote_average, poster_path));
            }
        }
        else {
            filmArrayList.add(new Film("data kosong","data kosong","data kosong","data kosong"));
        }
        listFilmMut.postValue(filmArrayList);
        filmHelper.close();
    }


    public LiveData<ArrayList<Film>> getFilmFav(){
        return listFilmMut;
    }

}
