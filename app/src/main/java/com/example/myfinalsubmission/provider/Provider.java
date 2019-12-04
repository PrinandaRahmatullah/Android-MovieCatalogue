package com.example.myfinalsubmission.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfinalsubmission.database.DatabaseContract;
import com.example.myfinalsubmission.database.FilmHelper;
import com.example.myfinalsubmission.database.TvHelper;

import java.util.Objects;

import static com.example.myfinalsubmission.database.DatabaseContract.CONTENT_AUTHORITY;
import static com.example.myfinalsubmission.database.DatabaseContract.FilmColumns.CONTENT_URI;
import static com.example.myfinalsubmission.database.DatabaseContract.TABLE_FILM;
import static com.example.myfinalsubmission.database.DatabaseContract.TABLE_TV;

public class Provider extends ContentProvider {
//    private static final int FILM = 1;
//    private static final int FILM_ID = 2;
//    private FilmHelper filmHelper;
//
//    public Provider() {
//    }
//
//    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//    static {
//        // content://com.dicoding.picodiploma.mynotesapp/note
//        sUriMatcher.addURI(CONTENT_AUTHORITY, TABLE_FILM, FILM);
//        // content://com.dicoding.picodiploma.mynotesapp/note/id
//        sUriMatcher.addURI(CONTENT_AUTHORITY,
//                TABLE_FILM + "/#",
//                FILM_ID);
//    }
//
//    @Override
//    public boolean onCreate() {
//        filmHelper = FilmHelper.getInstance(getContext());
//        filmHelper.open();
//        return true;
//    }
//
//    @Override
//    public Cursor query(Uri uri, String[] projection, String selection,
//                        String[] selectionArgs, String sortOrder) {
//        Cursor cursor;
//        switch (sUriMatcher.match(uri)) {
//            case FILM:
//                cursor = filmHelper.queryAll();
//                break;
//            case FILM_ID:
//                cursor = filmHelper.queryByName(uri.getLastPathSegment());
//                break;
//            default:
//                cursor = null;
//                break;
//        }
//        return cursor;
//    }
//
//
//    @Override
//    public String getType(Uri uri) {
//        // TODO: Implement this to handle requests for the MIME type of the data
//        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        long added;
//        if (sUriMatcher.match(uri) == FILM) {
//            added = filmHelper.insert(values);
//        } else {
//            added = 0;
//        }
//        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);
//        return Uri.parse(CONTENT_URI + "/" + added);
//    }
//
//
//    @Override
//    public int update(Uri uri, ContentValues values, String selection,
//                      String[] selectionArgs) {
//        int updated;
//        if (sUriMatcher.match(uri) == FILM_ID) {
//            updated = filmHelper.update(uri.getLastPathSegment(), values);
//        } else {
//            updated = 0;
//        }
//        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);
//        return updated;
//    }
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        int deleted;
//        if (sUriMatcher.match(uri) == FILM_ID) {
//            deleted = filmHelper.deleteById(uri.getLastPathSegment());
//        } else {
//            deleted = 0;
//        }
//        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, null);
//        return deleted;
//    }
//
    private static final int TV_SHOW = 1;
    private static final int TV_SHOW_ID = 2;
    private static final int MOVIE = 5;
    private static final int MOVIE_ID = 6;
    private FilmHelper filmHelper;
    private TvHelper tvHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(CONTENT_AUTHORITY, TABLE_FILM, MOVIE);
        sUriMatcher.addURI(CONTENT_AUTHORITY, TABLE_TV, TV_SHOW);
        sUriMatcher.addURI(CONTENT_AUTHORITY,TABLE_FILM + "/#", MOVIE_ID);
        sUriMatcher.addURI(CONTENT_AUTHORITY,TABLE_TV + "/#", TV_SHOW_ID);
    }

    public Provider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
                deleted = filmHelper.deleteById(uri.getLastPathSegment());
                break;
            case TV_SHOW_ID:
                deleted = tvHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(
//                DatabaseMovieCatalogueContract.MovieColumns.CONTENT_URI,
                uri,
                null);
        return deleted;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                added = filmHelper.insert(values);
                break;
            case TV_SHOW:
                added = tvHelper.insert(values);
                break;
            default:
                added = 0;
                break;
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(
//                DatabaseMovieCatalogueContract.MovieColumns.CONTENT_URI,
                uri,
                null);
        return Uri.parse(uri//DatabaseMovieCatalogueContract.MovieColumns.CONTENT_URI
                + "/" + added);
    }

    @Override
    public boolean onCreate() {
        filmHelper = FilmHelper.getInstance(getContext());
        tvHelper = TvHelper.getInstance(getContext());
        filmHelper.open();
        tvHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                cursor = filmHelper.queryAll();
                break;
            case MOVIE_ID:
                cursor = filmHelper.queryByName(uri.getLastPathSegment());
                break;
            case TV_SHOW:
                cursor = tvHelper.queryAll();
                break;
            case TV_SHOW_ID:
                cursor = tvHelper.queryByName(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
