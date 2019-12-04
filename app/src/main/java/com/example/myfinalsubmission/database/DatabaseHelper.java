package com.example.myfinalsubmission.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_FILM = String.format("CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_FILM,
            DatabaseContract.FilmColumns._ID,
            DatabaseContract.FilmColumns.TITLE_FILM,
            DatabaseContract.FilmColumns.OVERVIEW_FILM,
            DatabaseContract.FilmColumns.VOTE_AVERAGE_FILM,
            DatabaseContract.FilmColumns.POSTER_PATH_FILM
    );
    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            DatabaseContract.TABLE_TV,
            DatabaseContract.TvColumns._ID,
            DatabaseContract.TvColumns.TITLE_TV,
            DatabaseContract.TvColumns.OVERVIEW_TV,
            DatabaseContract.TvColumns.VOTE_AVERAGE_TV,
            DatabaseContract.TvColumns.POSTER_PATH_TV
    );
    private static String DATABASE_NAME = "moviedb";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FILM);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FILM);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_TV);
        onCreate(db);
    }
}

