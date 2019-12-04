package com.example.myfinalsubmission.viewmodel;

import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myfinalsubmission.database.DatabaseContract;
import com.example.myfinalsubmission.database.TvHelper;
import com.example.myfinalsubmission.model.Tv;

import java.util.ArrayList;

public class TvViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Tv>> listTvMut = new MutableLiveData<>();

    public LiveData<ArrayList<Tv>> getTvFav() {
        return listTvMut;
    }

    public void setTvFav(Context context) {

        ArrayList<Tv> tvArrayList = new ArrayList<>();
        TvHelper tvHelper = new TvHelper(context);
        tvHelper.open();

        Cursor cursor = tvHelper.queryAll();

        if (cursor != null) {

            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.TITLE_TV));
                String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.OVERVIEW_TV));
                String vote_average = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.VOTE_AVERAGE_TV));
                String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.POSTER_PATH_TV));
                tvArrayList.add(new Tv(title, overview, vote_average, poster_path));
            }
        } else {
            tvArrayList.add(new Tv("data kosong", "data kosong", "data kosong", "data kosong"));
        }
        listTvMut.postValue(tvArrayList);
        tvHelper.close();
    }

}
