package com.app.cellstudio.presentation;

import android.app.Application;

import com.app.cellstudio.presentation.di.components.ApplicationComponent;
import com.app.cellstudio.presentation.di.components.DaggerApplicationComponent;
import com.app.cellstudio.presentation.di.modules.ApplicationModule;
import com.app.cellstudio.presentation.presentation.util.image.BaseImageLoader;

/**
 * Created by coyan on 29/11/2018.
 */

public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    private final Object mLock = new Object();

    // Singleton Instance
    private static BaseApplication singleton;

    public static BaseApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//
//        singleton = this;
//        mApplicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
//        mApplicationComponent.inject(this);
        synchronized (mLock) {
            singleton = this;
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(this);
        }
        initializeSDKs();
    }

    private void initializeSDKs() {
        BaseImageLoader.getInstance().init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
