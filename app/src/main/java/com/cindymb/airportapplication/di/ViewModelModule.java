package com.cindymb.airportapplication.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cindymb.airportapplication.viewModel.FlightScheduleViewModel;
import com.cindymb.airportapplication.viewModel.NearbyAirportViewModel;

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


}
