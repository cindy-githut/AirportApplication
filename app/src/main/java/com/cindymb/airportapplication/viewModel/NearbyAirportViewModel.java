package com.cindymb.airportapplication.viewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cindymb.airportapplication.model.NearbyAirportModel;
import com.cindymb.airportapplication.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.services.NearbyAirportResponse;
import com.cindymb.airportapplication.services.repositories.NearbyAirportRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NearbyAirportViewModel extends ViewModel {
    @NonNull
    private final Application mApplication;
    private final MutableLiveData<List<NearbyAirportModel>> mNearbyAirportList = new MutableLiveData<>();
    private final Observer<NearbyAirportResponse> response = aNearbyAirportResponse -> {
        if (aNearbyAirportResponse != null) {
            ArrayList<NearbyAirportModel> nearbyAirportListModel = aNearbyAirportResponse.getData();
            if (nearbyAirportListModel != null && nearbyAirportListModel.size() > 0) {
                mNearbyAirportList.setValue(nearbyAirportListModel);
            }
        }
    };

    private NearbyAirportRepository mNearbyAirportRepository;

    @Inject
    NearbyAirportViewModel(@NonNull Application application, NearbyAirportRepository nearbyAirportRepository) {

        mApplication = application;
        mNearbyAirportRepository = nearbyAirportRepository;
        mNearbyAirportRepository.getNearbyAirportResponse().observeForever(response);
    }

    public void getNearbyAirportListAPI(NearbyAirportRequestModel nearbyAirportRequestModel) {
        mNearbyAirportRepository.getNearbyAirportList(nearbyAirportRequestModel);
    }

    public MutableLiveData<List<NearbyAirportModel>> getNearbyAirportList() {
        return mNearbyAirportList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mNearbyAirportRepository.getNearbyAirportResponse().removeObserver(response);
    }
}
