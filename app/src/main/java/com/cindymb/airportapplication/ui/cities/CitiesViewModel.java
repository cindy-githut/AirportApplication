package com.cindymb.airportapplication.ui.cities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.cindymb.airportapplication.services.repositories.CitiesRepository;

import java.util.List;

import javax.inject.Inject;

public class CitiesViewModel extends ViewModel {
    private final MutableLiveData<List<CitiesModel>> mLiveCitiesList = new MutableLiveData<>();
    private final Observer<List<CitiesModel>> response = aCitiesResponse -> {
        if (aCitiesResponse != null && aCitiesResponse.size() > 0) {
            mLiveCitiesList.setValue(aCitiesResponse);
        }
    };
    private CitiesRepository mCitiesRepository;

    @Inject
    CitiesViewModel(CitiesRepository citiesRepository) {

        mCitiesRepository = citiesRepository;
        mCitiesRepository.getMutableLiveData().observeForever(response);
    }

    public void getCitiesResponseFromAPI(String codeIataCity) {
        mCitiesRepository.getCityDetails(codeIataCity);
    }

   public  MutableLiveData<List<CitiesModel>> getMutableLiveCitiesResponse() {
        return mLiveCitiesList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCitiesRepository.getMutableLiveData().removeObserver(response);
    }
}
