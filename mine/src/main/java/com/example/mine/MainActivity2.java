package com.example.mine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mine.adapter.FilmAdapter;
import com.example.mine.fragment.FilmFavoriteFragment;
import com.example.mine.fragment.TvFavoriteFragment;
import com.example.mine.model.Film;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public static final String CONTENT_AUTHORITY = "com.example.myfinalsubmission";
    public static String TABLE_FILM = "film";
    FilmAdapter filmAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Cursor cursor;


    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            if (menuItem.getItemId() == R.id.nav_fav_movie){
                fragment = new FilmFavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.nav_fav_tv_show){
                fragment = new TvFavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null){
            bottomNavigationView.setSelectedItemId(R.id.nav_fav_movie);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about){
            Intent aboutIntent = new Intent(MainActivity2.this, AboutMe.class);
            startActivity(aboutIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
