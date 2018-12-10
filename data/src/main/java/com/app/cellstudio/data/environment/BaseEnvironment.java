package com.app.cellstudio.data.environment;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by coyan on 29/11/2018.
 */

public class BaseEnvironment implements Environment {

    private final Context context;
    public BaseEnvironment(Context context) { this.context = context; }

    @Override
    public String getAppName() {
        return "";
    }

    @Override
    public String getPackageName() {
        return "";
    }

    @Override
    public String getAppVersion() {
        return "";
    }

    @Override
    public String getBuildNumber() {
        return "";
    }

    @Override
    public String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @Override
    public String getDeviceName() {
        return android.os.Build.MODEL;
    }

    @Override
    public String getOSName() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    @Override
    public String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override
    public String getBaseUrl() {
        return "https://tv-v2.api-fetch.website/";
    }
}
