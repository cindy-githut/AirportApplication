package com.cindymb.airportapplication.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cindymb.airportapplication.ui.cities.CitiesViewModel;
import com.cindymb.airportapplication.ui.nearby.NearbyAirportViewModel;
import com.cindymb.airportapplication.ui.schedule.FlightScheduleViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(MyViewModelFactory aMyViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(NearbyAirportViewModel.class)
    public abstract ViewModel provideNearbyAirportViewModel(NearbyAirportViewModel nearbyAirportViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FlightScheduleViewModel.class)
    public abstract ViewModel provideFlightScheduleViewModel(FlightScheduleViewModel flightScheduleViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel.class)
    public abstract ViewModel provideCitiesViewModel(CitiesViewModel citiesModel);

}
