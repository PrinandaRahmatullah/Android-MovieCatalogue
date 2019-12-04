package com.example.myfinalsubmission;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myfinalsubmission.fragment.FilmFavoriteFragment;
import com.example.myfinalsubmission.fragment.FilmFragment;
import com.example.myfinalsubmission.fragment.TvFavoriteFragment;
import com.example.myfinalsubmission.fragment.TvFragment;
import com.example.myfinalsubmission.reminder.ReminderActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//import com.example.myfinalsubmission.reminder.ReminderActivity;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            if (menuItem.getItemId() == R.id.nav_movie) {
                fragment = new FilmFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.nav_tv_show) {
                fragment = new TvFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.nav_fav_movie) {
                fragment = new FilmFavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.nav_fav_tv_show) {
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
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.change_setting) {
            Intent settingIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(settingIntent);
        }
        if (item.getItemId() == R.id.about) {
            Intent aboutIntent = new Intent(MainActivity.this, AboutMe.class);
            startActivity(aboutIntent);
        }
        if (item.getItemId() == R.id.reminder_setting) {
            Intent reminderIntent = new Intent(this, ReminderActivity.class);
            startActivity(reminderIntent);
        }

        return super.onOptionsItemSelected(item);
    }

//    public void collapseSearchView() {
//        searchItem.collapseActionView();
//    }
}
