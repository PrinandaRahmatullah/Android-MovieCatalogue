package com.example.mine.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_FILM = "film";
    static String TABLE_TV = "tv";

    public static final String CONTENT_AUTHORITY = "com.example.myfinalsubmission";

    public static final class FilmColumns implements BaseColumns {
        public static String TITLE_FILM = "title";
        public static String OVERVIEW_FILM = "overview";
        public static String VOTE_AVERAGE_FILM = "vote_average";
        public static String POSTER_PATH_FILM="poster_path";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
                .authority(CONTENT_AUTHORITY)
                .appendPath(TABLE_FILM)
                .build();
    }

    public static final class TvColumns implements BaseColumns{
        public static String TITLE_TV = "name";
        public static String OVERVIEW_TV = "overview";
        public static String VOTE_AVERAGE_TV = "vote_average";
        public static String POSTER_PATH_TV="poster_path";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
                .authority(CONTENT_AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
    }
}
