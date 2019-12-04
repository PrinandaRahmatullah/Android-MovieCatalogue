package com.example.mine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mine.database.FilmHelper;
import com.example.mine.database.TvHelper;
import com.example.mine.model.Film;
import com.example.mine.model.Tv;

import java.util.Objects;

public class Detail extends AppCompatActivity {

    public static final String ExtraString = "name" ;
    public static final String putFrom = "from";
    public static final String movie = "movie";
    public static final String tv = "tv";
    public static final String movieFav = "movieFav";
    public static final String tvFav= "tvFav";

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
            if (From.equals(movie)|From.equals(movieFav)) {
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

            } else if (From.equals(tv)|From.equals(tvFav)){

                tvHelper= TvHelper.getInstance(this);
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
    protected void onDestroy() {
        super.onDestroy();
    }
}

