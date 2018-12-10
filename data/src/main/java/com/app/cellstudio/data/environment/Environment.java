package com.app.cellstudio.data.environment;

/**
 * Created by coyan on 29/11/2018.
 */

public interface Environment {
    String getAppName();
    String getPackageName();
    String getAppVersion();
    String getBuildNumber();
    String getDeviceId();
    String getDeviceName();
    String getOSName();
    String getOSVersion();
    String getBaseUrl();
}
