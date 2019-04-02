package com.cindymb.airportapplication.di;

import android.app.Application;

import com.cindymb.airportapplication.base.BaseRepository;
import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.services.repositories.NearbyAirportRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    BaseRepository provideBaseRepository(Application aApplication) {
        return new BaseRepository(aApplication);
    }

    @Provides
    NearbyAirportRepository provideNearbyAirportRepository(Application aApplication, ApiService aApiService) {
        return new NearbyAirportRepository(aApplication, aApiService);
    }
}
