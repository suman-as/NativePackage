package com.progressbarnotification;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class BackgroundNotificationService extends ReactContextBaseJavaModule {
    public BackgroundNotificationService(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "BackgroundNotificationService";
    }

    @ReactMethod
    public void startBackgroundService(ReadableMap params) {
        Intent serviceIntent = new Intent(getReactApplicationContext(), ProgressBarNotification.class);
        if (params != null) {
            serviceIntent.putExtra("max", params.getString("max"));
            serviceIntent.putExtra("current", params.getString("current"));
        }
        getReactApplicationContext().startService(serviceIntent);
    }
}
