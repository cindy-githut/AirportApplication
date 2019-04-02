package com.cindymb.airportapplication.application;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.cindymb.airportapplication.BuildConfig;
import com.cindymb.airportapplication.di.AppComponent;
import com.cindymb.airportapplication.di.AppModule;
import com.cindymb.airportapplication.di.DaggerAppComponent;
import com.cindymb.airportapplication.di.RepositoryModule;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class AirportApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        AppComponent appComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(new AppModule(this))
                .repoModule(new RepositoryModule())
                .build();
        appComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }
}
