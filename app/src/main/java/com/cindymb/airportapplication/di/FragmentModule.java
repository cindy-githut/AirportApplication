package com.cindymb.airportapplication.di;


import com.cindymb.airportapplication.fragment.FlightScheduleFragment;
import com.cindymb.airportapplication.fragment.MapsFragment;
import com.cindymb.airportapplication.fragment.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract MapsFragment mapsFragment();

    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

    @ContributesAndroidInjector
    abstract FlightScheduleFragment departureFragment();
}
