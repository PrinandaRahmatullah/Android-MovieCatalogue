package com.example.myfinalsubmission;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myfinalsubmission.database.DatabaseContract;
import com.example.myfinalsubmission.database.FilmHelper;
import com.example.myfinalsubmission.database.TvHelper;
import com.example.myfinalsubmission.model.Film;
import com.example.myfinalsubmission.model.Tv;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    public static final String ExtraString = "name";
    public static final String putFrom = "from";
    public static final String movie = "movie";
    public static final String tv = "tv";
    public static final String movieFav = "movieFav";
    public static final String tvFav = "tvFav";

    String From = movie;

    FilmHelper filmHelper;
    TvHelper tvHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        From = getIntent().getStringExtra(putFrom);

        TextView titleDetail = findViewById(R.id.tv_item_title);
        TextView descDetail = findViewById(R.id.tv_item_description);
        TextView voteAverage = findViewById(R.id.tv_item_rating);
        ImageView ivDetail = findViewById(R.id.img_item_photo);

        if (From != null) {
            if (From.equals(movie) | From.equals(movieFav)) {
                filmHelper = FilmHelper.getInstance(this);
                filmHelper.open();

                Film film = getIntent().getParcelableExtra(ExtraString);
                if (film != null) {
                    titleDetail.setText(film.getTitle());
                    descDetail.setText(film.getDescription());
                    voteAverage.setText("Rating " + film.getUserScore());

                    Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w185" + film.getPhoto())
                            .into(ivDetail);

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(film.getTitle());
                    }
                }

            } else if (From.equals(tv) | From.equals(tvFav)) {

                tvHelper = TvHelper.getInstance(this);
                tvHelper.open();
                Tv film = getIntent().getParcelableExtra(ExtraString);
                if (film != null) {
                    titleDetail.setText(film.getName());
                    descDetail.setText(film.getDescription());
                    voteAverage.setText("Rating " + film.getUserScore());

                    Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w185" + film.getPhoto())
                            .into(ivDetail);

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(film.getName());
                    }
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (From.equals(movie)) {
            getMenuInflater().inflate(R.menu.menu_fav, menu);
        } else if (From.equals(tv)) {
            getMenuInflater().inflate(R.menu.menu_fav, menu);
        } else if (From.equals(movieFav)) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        } else if (From.equals(tvFav)) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            if (From.equals(movie)) {
                insertFavFilm();
            } else if (From.equals(tv)) {
                insertFavTv();
            }
        }
        if (item.getItemId() == R.id.delete) {
            if (From.equals(movieFav)) {
                deleteFavFilm();
            } else if (From.equals(tvFav)) {
                deleteFavTv();
            }
            Toast.makeText(this, "Delete from Favorite", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertFavTv() {
        Tv data = getIntent().getParcelableExtra(ExtraString);

        if (data != null) {
            if (TvHelper.queryByName(data.getName()).getCount() < 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.TvColumns.TITLE_TV, Objects.requireNonNull(data).getName());
                contentValues.put(DatabaseContract.TvColumns.OVERVIEW_TV, Objects.requireNonNull(data).getDescription());
                contentValues.put(DatabaseContract.TvColumns.VOTE_AVERAGE_TV, Objects.requireNonNull(data).getUserScore());
                contentValues.put(DatabaseContract.TvColumns.POSTER_PATH_TV, Objects.requireNonNull(data).getPhoto());
                tvHelper.insert(contentValues);
                Toast.makeText(this, "Add to Favorite", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Already added", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void insertFavFilm() {
        Film data = getIntent().getParcelableExtra(ExtraString);

        if (data != null) {
            if (filmHelper.queryByName(data.getTitle()).getCount() < 1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.FilmColumns.TITLE_FILM, Objects.requireNonNull(data).getTitle());
                contentValues.put(DatabaseContract.FilmColumns.OVERVIEW_FILM, Objects.requireNonNull(data).getDescription());
                contentValues.put(DatabaseContract.FilmColumns.VOTE_AVERAGE_FILM, Objects.requireNonNull(data).getUserScore());
                contentValues.put(DatabaseContract.FilmColumns.POSTER_PATH_FILM, Objects.requireNonNull(data).getPhoto());
                filmHelper.insert(contentValues);
                Toast.makeText(this, "Add to Favorite", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Already Added", Toast.LENGTH_LONG).show();
            }
        }
    }


    void deleteFavFilm() {
        Film data = getIntent().getParcelableExtra(ExtraString);
        filmHelper.deleteByName(Objects.requireNonNull(data).getTitle());
        finish();
    }

    void deleteFavTv() {
        Tv data = getIntent().getParcelableExtra(ExtraString);
        tvHelper.deleteByName(Objects.requireNonNull(data).getName());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
