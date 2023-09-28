package com.progressbarnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;



import com.facebook.react.bridge.Callback;

public class ProgressBarNotification extends Service {

    private static final String CHANNEL_ID = "your_channel_id";
    private static ReactApplicationContext reactContext;


//    ProgressBarNotification(ReactApplicationContext context) {
//        super(reactContext);
//        reactContext = context;
//    }


    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Handle notifications here based on the intent or event
        Context context=getApplicationContext();
        if (intent != null) {
            int max = Integer.parseInt(intent.getStringExtra("max"));
            int current = Integer.parseInt(intent.getStringExtra("current"));

            showProgressNotification(context,10, "Start", "Ready", max, current);
        }

        return Service.START_STICKY;
    }

    // @Override
    // public String getName() {
    // return "ProgressBarNotification";
    // }

    @ReactMethod
    public void showProgressNotification(Context context,int notificationId, String title, String content, int PROGRESS_MAX,
            int PROGRESS_CURRENT) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("myChannelId", "Channel Name", NotificationManager.IMPORTANCE_HIGH);
//            NotificationManager notificationManager =(NotificationManager) context.getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel for Android Oreo and later
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Your Channel Name",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setOngoing(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());
        Log.d("Notify",String.valueOf(PROGRESS_CURRENT));

        // Issue the initial notification with zero progress.

//        notificationManager.notify(notificationId, builder.build());
//        Notification notification = builder.build();

        // Simulate progress update here, usually in a worker thread
        // To show progress, update PROGRESS_CURRENT and update the notification
        // builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        // notificationManager.notify(notificationId, builder.build());

        // When done, update the notification to remove the progress bar.
        // builder.setContentText("Download complete")
        // .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        // notificationManager.notify(notificationId, builder.build());
    }

}
