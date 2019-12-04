//package com.example.myfinalsubmission;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.Switch;
//import android.widget.Toast;
//
//import com.example.myfinalsubmission.reminder.DailyReminder;
//import com.example.myfinalsubmission.reminder.ReleaseReminder;
//
//public class Setting extends AppCompatActivity {
//
//    Switch switch1,switch2;
//    SharedPreferences sharedPreferences;
//    SharedPreferences.Editor editor;
//    DailyReminder reminder;
//    ReleaseReminder releaseToday;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//
//        sharedPreferences=this.getSharedPreferences("setting",MODE_PRIVATE);
//
//        editor=sharedPreferences.edit();
//        reminder= new DailyReminder();
//        releaseToday= new ReleaseReminder();
//
//        switch1 =findViewById(R.id.switch_daily);
//        switch1.setChecked(sharedPreferences.getBoolean("sw1",false));
//        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                editor.putBoolean("sw1",isChecked);
//                editor.apply();
//                String p;
//                if (isChecked) {
//                    p="on";
//                }else{
//                    p="off";
//                }
//                Toast.makeText(Setting.this,"daily reminder is "+ p,Toast.LENGTH_LONG).show();
//                reminder.dailyReminder(Setting.this,isChecked);
//            }
//        });
//
//        switch2 =findViewById(R.id.switch_release);
//        switch2.setChecked(sharedPreferences.getBoolean("sw2",false));
//        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                editor.putBoolean("sw2",isChecked);
//                editor.apply();
//                String p;
//                if (isChecked) {
//                    p="on";
//                }else{
//                    p="off";
//                }
//                Toast.makeText(Setting.this,"Release today is "+ p,Toast.LENGTH_LONG).show();
//                releaseToday.setReleaseToday(Setting.this,isChecked);
//            }
//        });
//    }
//}
