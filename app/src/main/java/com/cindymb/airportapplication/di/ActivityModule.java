package com.cindymb.airportapplication.di;

import com.cindymb.airportapplication.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}