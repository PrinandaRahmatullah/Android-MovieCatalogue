package com.example.myfinalsubmission.reminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myfinalsubmission.R;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener{
    private Switch dailySwitch, releaseSwitch;
    private DailyReminder dailyReminder;
    private ReleaseReminder releaseReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.reminder_preferences);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        dailySwitch = findViewById(R.id.switch_daily);
        releaseSwitch = findViewById(R.id.switch_release);
        dailyReminder = new DailyReminder();
        releaseReminder = new ReleaseReminder();

        if (dailyReminder.isDailyReminderSet(this)) {
            dailySwitch.setChecked(true);
        } else {
            dailySwitch.setChecked(false);
        }

        if (releaseReminder.isReleaseReminderSet(this)) {
            releaseSwitch.setChecked(true);
        } else {
            releaseSwitch.setChecked(false);
        }

        dailySwitch.setOnClickListener(this);
        releaseSwitch.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.switch_daily:
                if (dailySwitch.isChecked()) {
                    dailyReminder.setDailyReminder(this);
                    Toast.makeText(this, getString(R.string.daily_reminder_on), Toast.LENGTH_SHORT).show();
                } else {
                    dailyReminder.cancelDailyReminder(this);
                    Toast.makeText(this, getString(R.string.daily_reminder_off), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.switch_release:
                if (releaseSwitch.isChecked()) {
                    releaseReminder.setReleaseReminder(this);
                    Toast.makeText(this, getString(R.string.release_reminder_on), Toast.LENGTH_SHORT).show();
                } else {
                    releaseReminder.cancelReleaseReminder(this);
                    Toast.makeText(this, getString(R.string.release_reminder_off), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
