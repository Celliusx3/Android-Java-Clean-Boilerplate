package com.app.cellstudio.presentation.di.modules;

import android.content.Context;

import com.app.cellstudio.data.environment.BaseEnvironment;
import com.app.cellstudio.data.environment.Environment;
import com.app.cellstudio.data.http.BaseHttpClient;
import com.app.cellstudio.data.http.HttpClient;
import com.app.cellstudio.presentation.BaseApplication;
import com.app.cellstudio.presentation.interactor.scheduler.BaseSchedulerProvider;
import com.app.cellstudio.presentation.interactor.scheduler.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by coyan on 29/11/2018.
 */

@Module
public class ApplicationModule {
    private final BaseApplication mApplication;

    public ApplicationModule(final BaseApplication baseApplication) {
        mApplication = baseApplication;
    }

    @Provides
    @Singleton
    BaseApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext(BaseApplication baseApplication) {
        return baseApplication;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideScheduler() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    HttpClient provideHttpClient(Environment environment) {
        return new BaseHttpClient(environment);
    }

    @Provides
    @Singleton
    Environment provideEnvironment(Context context) {
        return new BaseEnvironment(context);
    }

//    @Provides
//    @Singleton
//    SharedPreferences provideSharedPreferences(Context context) {
//        if (mPrefs == null) {
//            String key = context.getPackageName();
//            if (key == null) {
//                throw new NullPointerException("Prefs key may not be null");
//            }
//            mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
//        }
//        return mPrefs;
//    }
//
//    @Provides
//    @Singleton
//    BasePref provideBasePref(Context context, SharedPreferences sharedPreferences) {
//        return new BaseSharedPref(context, sharedPreferences);
//    }
}
