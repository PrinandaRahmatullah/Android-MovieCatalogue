package com.example.myfinalsubmission.widget;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.myfinalsubmission.R;
import com.example.myfinalsubmission.database.DatabaseContract;
import com.example.myfinalsubmission.model.Film;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.myfinalsubmission.database.DatabaseContract.FilmColumns.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final ArrayList<Film> filmList = new ArrayList<>();
    private final Context mContext;
    private Cursor cursor;

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        cursor = mContext.getContentResolver().query(
                CONTENT_URI,
                null,
                null,
                null,
                null,
                null
        );

        while (Objects.requireNonNull(cursor).moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.TITLE_FILM));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.OVERVIEW_FILM));
            String vote_average = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.VOTE_AVERAGE_FILM));
            String poster_path = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.POSTER_PATH_FILM));
            filmList.add(new Film(title, overview, vote_average, poster_path));
        }

    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        // querying ke database
        cursor = mContext.getContentResolver().query(CONTENT_URI, null, null, null, null);

        Binder.restoreCallingIdentity(identityToken);
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return filmList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w185" + filmList.get(position).getPhoto())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        remoteViews.setImageViewBitmap(R.id.img_widget, bitmap);
        remoteViews.setTextViewText(R.id.widget_title, filmList.get(position).getTitle());

        return remoteViews;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
