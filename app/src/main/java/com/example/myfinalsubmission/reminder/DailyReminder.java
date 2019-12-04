package com.example.myfinalsubmission.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.myfinalsubmission.MainActivity;
import com.example.myfinalsubmission.R;

import java.util.Calendar;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DailyReminder extends BroadcastReceiver {
//    int ID_reminder = 99;
//    private CharSequence CHANNEL_NAME = "My Channel";
//
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        notification(context);
//        Log.e(TAG, "onReceive: aktif");
//    }
//
//
//    public void dailyReminder(Context context, Boolean status) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        if (status) {
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, 7);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//
//            Intent intent = new Intent(context, DailyReminder.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_reminder, intent, 0);
//            if (alarmManager != null) {
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//            }
//        } else {
//            Intent intent = new Intent(context, DailyReminder.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_reminder, intent, 0);
//            pendingIntent.cancel();
//            if (alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//            }
//        }
//    }
//
//    private void notification(Context context) {
//        Intent intent = new Intent(context, MainActivity.class);
//
////        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        PendingIntent pendingIntent = TaskStackBuilder.create(context)
//                .addParentStack(MainActivity.class)
//                .addNextIntent(intent)
//                .getPendingIntent(ID_reminder, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        String CHANNEL_ID = "channel_99";
//
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setContentIntent(pendingIntent)
//                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifications_black_24dp))
//                .setContentTitle(context.getResources().getString(R.string.app_name))
//                .setContentText(context.getResources().getString(R.string.back_application))
//                .setSubText(context.getResources().getString(R.string.daily_description))
//                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
//                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setAutoCancel(true);
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            mBuilder.setChannelId(CHANNEL_ID);
//
//            if (mNotificationManager != null) {
//                mNotificationManager.createNotificationChannel(channel);
//            }
//        }
//
//        Notification notification = mBuilder.build();
//
//        if (mNotificationManager != null) {
//            int NOTIFICATION_ID = 22;
//            mNotificationManager.notify(NOTIFICATION_ID, notification);
//        }
//    }

    public static final int ID_DAILY_REMINDER = 123;
//    public static final String DAILY_REMINDER_NOTIFICATION = "daily_reminder";
//    public static final String NOTIFICATION_EXTRA = "notification_extra";

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = context.getString(R.string.app_name);
        String message = context.getString(R.string.back_application);

        showNotification(context, title, message, ID_DAILY_REMINDER);
    }

    public void setDailyReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        Intent intent = new Intent(context, DailyReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY_REMINDER, intent, 0);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelDailyReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminder.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_DAILY_REMINDER, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    public boolean isDailyReminderSet(Context context) {
        Intent mIntent = new Intent(context, DailyReminder.class);

        return PendingIntent.getBroadcast(context, ID_DAILY_REMINDER, mIntent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    private void showNotification(Context context, String title, String msg, int id) {
        String CHANNEL_ID = "channel_10";
        String CHANNEL_NAME = "daily_reminder";

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                CHANNEL_ID)
                .setSmallIcon(R.drawable.movie)
                .setContentTitle(title)
                .setContentText(msg)
                .setSound(uriSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

//      Notifikasi untuk >= Android Oreo (8.0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            builder.setChannelId(CHANNEL_ID);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
        Notification notification = builder.build();
        if (manager != null) {
            manager.notify(id, notification);
        }
    }

}
