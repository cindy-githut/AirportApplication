package com.cindymb.airportapplication.di;


import com.cindymb.airportapplication.ui.SplashFragment;
import com.cindymb.airportapplication.ui.nearby.NearbyFragment;
import com.cindymb.airportapplication.ui.schedule.FlightScheduleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract NearbyFragment nearbyFragment();

    @ContributesAndroidInjector
    abstract SplashFragment splashFragment();

    @ContributesAndroidInjector
    abstract FlightScheduleFragment departureFragment();
}
