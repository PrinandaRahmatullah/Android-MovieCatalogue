package com.example.myfinalsubmission.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.myfinalsubmission.BuildConfig;
import com.example.myfinalsubmission.MainActivity;
import com.example.myfinalsubmission.R;
import com.example.myfinalsubmission.model.Film;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ReleaseReminder extends BroadcastReceiver {
//    public final int ID_RELEASE_REMINDER = 456;
//    private final static String GROUP_NEW_MOVIES = "group_new_movies";
//    private ArrayList<Film> films = new ArrayList<>();
//
//    @Override
//    public void onReceive(final Context context, Intent intent) {
//        films = new ArrayList<>();
//        final String titleNotif = context.getString(R.string.new_film_release);
//        String todayDate = new SimpleDateFormat("yyyy-MM-dd",
//                Locale.getDefault()).format(new Date());
//
//        String url = " https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.API_KEY + "&primary_release_date.gte=" +  todayDate + "&primary_release_date.lte=" + todayDate;
//
//        Log.wtf("URL API ", url);
//        Log.wtf("TODAY DATE ", todayDate);
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                try {
//                    String result = new String(responseBody);
//                    JSONObject object = new JSONObject(result);
//                    JSONArray movieList = object.getJSONArray("results");
//
//                    for (int x = 0; x < movieList.length(); x++) {
//                        JSONObject objectrelease = movieList.getJSONObject(x);
//                        Film film = new Film();
//                        film.setTitle(objectrelease.getString("title"));
//                        film.setDescription(objectrelease.getString("overview"));
//                        film.setPhoto(objectrelease.getString("poster_path"));
//                        films.add(film);
//                    }
//
////                    String msg = "Hai, ada " + totalMovie + " Film yang baru rilis hari ini. Cek segera di aplikasi!";
//                    String msg = "New Movie Release: ";
//
//                    for (Film oneMovie : films) {
//                        msg = msg + oneMovie.getTitle();
//
//                        showReleaseNotification(context, titleNotif, msg, ID_RELEASE_REMINDER);
//                    }
//                } catch (Exception e) {
//                    Log.wtf("ERROR ", e.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.wtf("onFailed: ", error.getMessage());
//            }
//        });
//
//    }
//
//    private void showReleaseNotification(Context context, String titleNotif, String msg, int idRelease) {
//        String CHANNEL_ID = "channel_20";
//        String CHANNEL_NAME = "release_reminder";
//        int totalMovie = films.size();
//        NotificationCompat.Builder builder;
//
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);//Intent.FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, idRelease, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        if (totalMovie < 2) {
//            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle(titleNotif)
//                    .setContentText(msg)
//                    .setSmallIcon(R.drawable.movie)
//                    .setGroup(GROUP_NEW_MOVIES)
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true);
//        } else {
//            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
//                    .addLine(films.get(0).getTitle())
//                    .addLine(films.get(films.size() - 1).getTitle())
//                    .setBigContentTitle(titleNotif)
//                    .setSummaryText("Movie Catalogue");
//
//            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.movie)
//                    .setContentTitle(titleNotif)
//                    .setContentText(msg)
//                    .setContentIntent(pendingIntent)
//                    .setGroup(GROUP_NEW_MOVIES)
//                    .setGroupSummary(true)
//                    .setStyle(inboxStyle)
//                    .setSound(uriSound)
//                    .setAutoCancel(true);
//        }
//
////      Notifikasi untuk >= Android Oreo (8.0)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            builder.setChannelId(CHANNEL_ID);
//
//            if (manager != null) {
//                manager.createNotificationChannel(channel);
//            }
//        }
//        Notification notification = builder.build();
//        if (manager != null) {
//            manager.notify(idRelease, notification);
//        }
//    }
//
//    public boolean isReleaseReminderSet(Context context) {
//        Intent intent = new Intent(context, ReleaseReminder.class);
//
//        return PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, PendingIntent.FLAG_NO_CREATE) != null;
//    }
//
//    public void setReleaseReminder(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Calendar setCalender = Calendar.getInstance();
//        setCalender.set(Calendar.HOUR_OF_DAY, 8);
//        setCalender.set(Calendar.MINUTE, 0);
//        setCalender.set(Calendar.SECOND, 0);
//        setCalender.set(Calendar.AM_PM, Calendar.AM);
//
//        Intent intent = new Intent(context, ReleaseReminder.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, 0);
//
//        if (alarmManager != null) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setCalender.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        }
//    }
//
//    public void cancelReleaseReminder(Context context) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context, ReleaseReminder.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, 0);
//        pendingIntent.cancel();
//
//        if (alarmManager != null) {
//            alarmManager.cancel(pendingIntent);
//        }
//    }

//    private static final CharSequence CHANNEL_NAME = "My schannel";
//    private final static String MY_GROUP_KEY = "my_key";
//    private static final int MAX_NOTIFICATION = 3;
//    private final ArrayList<Film> stackNotif = new ArrayList<>();
//    int REQUESTCODE = 2002;
//    private int idNotification = 0;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        setData(context);
//    }
//
//    public void setReleaseToday(Context context, Boolean status) {
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//
//        if (status) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, 8);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//
//            Intent intent = new Intent(context, ReleaseReminder.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUESTCODE, intent, 0);
//            if (alarmManager != null) {
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//            }
//        } else {
//            Intent intent = new Intent(context, ReleaseReminder.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUESTCODE, intent, 0);
//            pendingIntent.cancel();
//            if (alarmManager != null) {
//                alarmManager.cancel(pendingIntent);
//            }
//        }
//    }
//
//    private void sendNotif(Context context) {
//        Log.e(TAG, "sendNotif: success");
//        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifications_black_24dp);
//
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUESTCODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder mBuilder;
//        String CHANNEL_ID = "channel_01";
//
//        if (idNotification < MAX_NOTIFICATION) {
//            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle("New movie" + stackNotif.get(idNotification).getTitle())
//                    .setContentText("Come and see it")
//                    .setSmallIcon(R.drawable.movie)
//                    .setLargeIcon(largeIcon)
//                    .setGroup(MY_GROUP_KEY)
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true);
//        } else {
//            Log.e(TAG, "sendNotif: stack notif");
//            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
//                    .addLine("New Movie " + stackNotif.get(idNotification).getTitle())
//                    .addLine("New Movie " + stackNotif.get(idNotification - 1).getTitle())
//                    .addLine("New Movie " + stackNotif.get(idNotification - 2).getTitle())
//                    .addLine("New Movie " + stackNotif.get(idNotification - 3).getTitle())
//                    .setBigContentTitle(idNotification + " new Movie")
//                    .setSummaryText("Movie Db");
//
//            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                    .setContentTitle(idNotification + " new movie")
//                    .setContentText("movie db")
//                    .setSmallIcon(R.drawable.movie)
//                    .setGroup(MY_GROUP_KEY)
//                    .setGroupSummary(true)
//                    .setContentIntent(pendingIntent)
//                    .setStyle(inboxStyle)
//                    .setColor(ContextCompat.getColor(context, android.R.color.transparent))
//                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                    .setAutoCancel(true);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            mBuilder.setChannelId(CHANNEL_ID);
//            if (mNotificationManager != null) {
//                mNotificationManager.createNotificationChannel(channel);
//            }
//        }
//        Notification notification = mBuilder.build();
//        if (mNotificationManager != null) {
//            mNotificationManager.notify(idNotification, notification);
//        }
//    }
//
//
//    private void setData(final Context context) {
//        idNotification = 0;
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String x = dateFormat.format(date);
//        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + BuildConfig.API_KEY + "&primary_release_date.gte=" + x + "&primary_release_date.lte=" + x;
//
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        final ArrayList<Film> arrayList = new ArrayList<>();
//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Log.e(TAG, "onSuccess: ");
//                try {
//                    String json = new String(responseBody);
//                    JSONObject responseObject = new JSONObject(json);
//                    JSONArray list = responseObject.getJSONArray("results");
//                    for (int i = 0; i < list.length(); i++) {
//                        JSONObject film = list.getJSONObject(i);
//                        Film filmObject = new Film();
//                        filmObject.setTitle(film.getString("title"));
//                        filmObject.setDescription(film.getString("overview"));
//                        filmObject.setPhoto(film.getString("poster_path"));
//                        filmObject.setUserScore(film.getString("vote_average"));
//                        arrayList.add(filmObject);
//                        idNotification = i;
//
//                        Log.e(TAG, "onSuccess: " + arrayList.size());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                stackNotif.addAll(arrayList);
//
//                Log.e(TAG, "onSuccess: " + stackNotif.size());
//
//                if (stackNotif.size() > 0) {
//                    Log.e(TAG, "setData: ");
//                    sendNotif(context);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.e("fail get", "onFailure: " + error.getMessage());
//            }
//        });
//
//
//    }

    public final int ID_RELEASE_REMINDER = 456;
    private final static String GROUP_NEW_MOVIES = "group_new_movies";
    private ArrayList<Film> film = new ArrayList<>();

    @Override
    public void onReceive(final Context context, Intent intent) {
        film = new ArrayList<>();
        final String titleNotif = context.getString(R.string.film_release_today);
        final String API_KEY = BuildConfig.API_KEY;
        String todayDate = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault()).format(new Date());

        String url = " https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY + "&primary_release_date.gte=" +  todayDate + "&primary_release_date.lte=" + todayDate;

        Log.wtf("URL API ", url);
        Log.wtf("TODAY DATE ", todayDate);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject object = new JSONObject(result);
                    JSONArray movieList = object.getJSONArray("results");

                    for (int x = 0; x < movieList.length(); x++) {
                        JSONObject movie = movieList.getJSONObject(x);
                        Film item = new Film();
                        item.setTitle(movie.getString("title"));
                        item.setUserScore(movie.getString("vote_average"));
                        item.setDescription(movie.getString("overview"));
                        item.setPhoto(movie.getString("poster_path"));
                        film.add(item);
                    }

//                    String msg = "Hai, ada " + totalMovie + " Film yang baru rilis hari ini. Cek segera di aplikasi!";
                    String msg = "New Movie Release: ";

                    for (Film oneMovie : film) {
                        msg = msg + oneMovie.getTitle();

                        showReleaseNotification(context, titleNotif, msg, ID_RELEASE_REMINDER);
                    }
                } catch (Exception e) {
                    Log.wtf("ERROR ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.wtf("onFailed: ", error.getMessage());
            }
        });

    }

    private void showReleaseNotification(Context context, String titleNotif, String msg, int idRelease) {
        String CHANNEL_ID = "channel_20";
        String CHANNEL_NAME = "release_reminder";
        int totalMovie = film.size();
        NotificationCompat.Builder builder;

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri uriSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);//Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, idRelease, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (totalMovie < 2) {
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(titleNotif)
                    .setContentText(msg)
                    .setSmallIcon(R.drawable.movie)
                    .setGroup(GROUP_NEW_MOVIES)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine(film.get(0).getTitle())
                    .addLine(film.get(film.size() - 1).getTitle())
                    .setBigContentTitle(titleNotif)
                    .setSummaryText("Movie Catalogue");

            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.movie)
                    .setContentTitle(titleNotif)
                    .setContentText(msg)
                    .setContentIntent(pendingIntent)
                    .setGroup(GROUP_NEW_MOVIES)
                    .setGroupSummary(true)
                    .setStyle(inboxStyle)
                    .setSound(uriSound)
                    .setAutoCancel(true);
        }

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
            manager.notify(idRelease, notification);
        }
    }

    public boolean isReleaseReminderSet(Context context) {
        Intent intent = new Intent(context, ReleaseReminder.class);

        return PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    public void setReleaseReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar setCalender = Calendar.getInstance();
        setCalender.set(Calendar.HOUR_OF_DAY, 8);
        setCalender.set(Calendar.MINUTE, 0);
        setCalender.set(Calendar.SECOND, 0);
        setCalender.set(Calendar.AM_PM, Calendar.AM);

        Intent intent = new Intent(context, ReleaseReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, 0);

        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, setCalender.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelReleaseReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_REMINDER, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
