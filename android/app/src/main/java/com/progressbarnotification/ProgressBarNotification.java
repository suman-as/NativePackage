package com.progressbarnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class ProgressBarNotification extends ReactContextBaseJavaModule {

    private static final String CHANNEL_ID = "your_channel_id";
    private static ReactApplicationContext reactContext;

    ProgressBarNotification(ReactApplicationContext context) {
        super(reactContext);
        reactContext = context;
    }

    @Override
    public String getName() {
        return "ProgressBarNotification";
    }

    @ReactMethod
    public void showProgressNotification(int notificationId, String title, String content,int PROGRESS_MAX,int PROGRESS_CURRENT,int coverImageResourceId) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(reactContext);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel for Android Oreo and later
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Your Channel Name",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(reactContext, CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setOngoing(true)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        //.bigPicture(R.drawable.ic_notification) // Set the cover image
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // Issue the initial notification with zero progress.

        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());

        // Simulate progress update here, usually in a worker thread
        // To show progress, update PROGRESS_CURRENT and update the notification
        // builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        // notificationManager.notify(notificationId, builder.build());

        // When done, update the notification to remove the progress bar.
        builder.setContentText("Download complete")
                .setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(notificationId, builder.build());
    }

}
