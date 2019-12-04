package com.example.myfinalsubmission.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String CONTENT_AUTHORITY = "com.example.myfinalsubmission";

    public static String TABLE_FILM = "film";
    public static String TABLE_TV = "tv";

    public static final class FilmColumns implements BaseColumns {
        public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
                .authority(CONTENT_AUTHORITY)
                .appendPath(TABLE_FILM)
                .build();
        public static String TITLE_FILM = "title";
        public static String OVERVIEW_FILM = "overview";
        public static String VOTE_AVERAGE_FILM = "vote_average";
        public static String POSTER_PATH_FILM = "poster_path";

    }

    public static final class TvColumns implements BaseColumns {
        public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
                .authority(CONTENT_AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
        public static String TITLE_TV = "name";
        public static String OVERVIEW_TV = "overview";
        public static String VOTE_AVERAGE_TV = "vote_average";
        public static String POSTER_PATH_TV = "poster_path";
    }
}
