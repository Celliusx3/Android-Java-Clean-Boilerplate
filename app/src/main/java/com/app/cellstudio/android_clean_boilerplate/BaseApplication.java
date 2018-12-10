package com.app.cellstudio.android_clean_boilerplate;

import android.app.Application;

/**
 * Created by coyan on 29/11/2018.
 */

public class BaseApplication extends Application {

//    private ApplicationComponent mApplicationComponent;
    private final Object mLock = new Object();

    // Singleton Instance
    private static BaseApplication singleton;

    public static BaseApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        synchronized (mLock) {
//            singleton = this;
//            mApplicationComponent = DaggerApplicationComponent.builder()
//                    .applicationModule(new ApplicationModule(this))
//                    .build();
//            mApplicationComponent.inject(this);
//        }
        initializeSDKs();
    }

    private void initializeSDKs() {
    }

//    public ApplicationComponent getApplicationComponent() {
//        return mApplicationComponent;
//    }

}
