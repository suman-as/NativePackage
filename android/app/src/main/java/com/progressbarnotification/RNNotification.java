package com.progressbarnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class RNNotification extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    RNNotification(ReactApplicationContext context) {
        super(reactContext);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "RNNotification";
    }

    @ReactMethod
    public void showNotification(ReadableMap params) {
        String channelID = params.getString("channelID");
        String channelName = params.hasKey("channelName") ? params.getString("channelName") : "Notify";
        int notificationID = params.getInt("notificationID");
        String title = params.getString("title");
        String content = params.getString("content");
        boolean onGoing = params.hasKey("onGoing") ? params.getBoolean("onGoing") : false;
        boolean isProgress = params.hasKey("isProgress") ? params.getBoolean("isProgress") : false;
        int priority = params.hasKey("priority") ? params.getInt("priority") : 0;
        int max = params.hasKey("max") ? params.getInt("max") : 0;
        int current = params.hasKey("current") ? params.getInt("current") : 0;
        boolean indeterminate = params.hasKey("indeterminate") ? params.getBoolean("indeterminate") : false;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(reactContext);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// Support android 8.0 or higher
            // Create a notification channel for Android
            NotificationChannel channel = new NotificationChannel(channelID, channelName,
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(reactContext, channelID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setOngoing(onGoing)
                .setPriority(priority);

        if (isProgress) {
            builder.setProgress(max, current, indeterminate);
        }
        notificationManager.notify(notificationID, builder.build());
    }

    @ReactMethod
    public void cancelNotification(ReadableMap params) {
        int notificationID = params.getInt("notificationID");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(reactContext);
        notificationManager.cancel(notificationID);
    }

    @ReactMethod
    public void cancelAllNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(reactContext);
        notificationManager.cancelAll();
    }

}
