package com.progressbarnotification;

import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class ProgressBarNotification extends ReactContextBaseJavaModule {

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
    public void add(int a, int b, Callback callback) {
        int sum = a + b;
        System.out.println(sum);
        callback.invoke(sum);
    }


}
