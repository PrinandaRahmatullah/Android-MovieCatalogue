package com.example.myfinalsubmission;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.myfinalsubmission.notification.AlarmReceiver;
import com.example.myfinalsubmission.reminder.DailyReminder;
import com.example.myfinalsubmission.reminder.ReleaseReminder;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        AlarmReceiver alarmReceiver;
        private SwitchPreferenceCompat dailyNotification, releaseNotification;

        String RELEASE_NOTIFICATION, DAILY_NOTIFICATION;
        Context context;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            alarmReceiver = new AlarmReceiver();
            DAILY_NOTIFICATION = "Daily Notification";
            RELEASE_NOTIFICATION = "Release Notification";

            dailyNotification = findPreference(DAILY_NOTIFICATION);
            releaseNotification = findPreference(RELEASE_NOTIFICATION);

            SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

            dailyNotification.setChecked(sharedPreferences.getBoolean(DAILY_NOTIFICATION, false));
            releaseNotification.setChecked(sharedPreferences.getBoolean(RELEASE_NOTIFICATION, false));
        }

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            this.context = context;
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals(DAILY_NOTIFICATION)){
                boolean setNotification = sharedPreferences.getBoolean(DAILY_NOTIFICATION, false);
                dailyNotification.setChecked(setNotification);

                if (setNotification){
                    alarmReceiver.setDailyNotification(context, AlarmReceiver.DAILY_MESSAGE);
                } else {
                    alarmReceiver.turnOffDailyNotification(context, AlarmReceiver.DAILY_MESSAGE);
                }
            }

            if (key.equals(RELEASE_NOTIFICATION)){
                boolean setReleaseNotification = sharedPreferences.getBoolean(RELEASE_NOTIFICATION, false);
                releaseNotification.setChecked(setReleaseNotification);

                if (setReleaseNotification){
                    alarmReceiver.setReleaseNotification(context, AlarmReceiver.RELEASE_MESSAGE);
                } else {
                    alarmReceiver.turnOffReleaseNotification(context, AlarmReceiver.RELEASE_MESSAGE);
                }
            }
        }
    }


//    Switch switchDaily, switchRelease;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    DailyReminder reminder;
//    ReleaseReminder releaseToday;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings_activity);
//
//        sharedPreferences = this.getSharedPreferences("setting", MODE_PRIVATE);
//
//        editor = sharedPreferences.edit();
//        reminder = new DailyReminder();
//        releaseToday = new ReleaseReminder();
//
//        switchDaily = findViewById(R.id.switch_daily);
//        switchDaily.setChecked(sharedPreferences.getBoolean("sw1", false));
//        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                editor.putBoolean("sw1", isChecked);
//                editor.apply();
//                String p;
//                if (isChecked) {
//                    p = "on";
//                } else {
//                    p = "off";
//                }
//                Toast.makeText(SettingsActivity.this, "daily reminder is " + p, Toast.LENGTH_LONG).show();
//                reminder.dailyReminder(SettingsActivity.this, isChecked);
//            }
//        });
//
//        switchRelease = findViewById(R.id.switch_release);
//        switchRelease.setChecked(sharedPreferences.getBoolean("sw2", false));
//        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                editor.putBoolean("sw2", isChecked);
//                editor.apply();
//                String p;
//                if (isChecked) {
//                    p = "on";
//                } else {
//                    p = "off";
//                }
//                Toast.makeText(SettingsActivity.this, "Release today is " + p, Toast.LENGTH_LONG).show();
//                releaseToday.setReleaseToday(SettingsActivity.this, isChecked);
//            }
//        });
//    }
}