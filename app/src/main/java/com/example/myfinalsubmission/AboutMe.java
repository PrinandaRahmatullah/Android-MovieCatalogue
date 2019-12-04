package com.example.myfinalsubmission;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About Me");
        }
    }
}
