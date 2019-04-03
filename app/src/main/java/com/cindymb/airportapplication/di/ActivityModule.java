package com.cindymb.airportapplication.di;

import com.cindymb.airportapplication.MainActivity;
import com.cindymb.airportapplication.base.BaseActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract BaseActivity bindBaseActivity();

}