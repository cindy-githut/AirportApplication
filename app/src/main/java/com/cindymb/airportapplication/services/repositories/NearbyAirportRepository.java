package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.cindymb.airportapplication.base.BaseRepository;
import com.cindymb.airportapplication.model.NearbyAirportModel;
import com.cindymb.airportapplication.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.services.ApiService;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyAirportRepository extends BaseRepository {
    private final MutableLiveData<List<NearbyAirportModel>> mMutableLiveData = new MutableLiveData<>();

    private final Application mApplication;
    private final ApiService mApiService;

    @Inject
    public NearbyAirportRepository(Application application, ApiService apiService) {
        super(application);
        mApplication = application;
        mApiService = apiService;
    }

    public void getNearbyAirportList(NearbyAirportRequestModel nearbyAirportRequestModel) {
        if (nearbyAirportRequestModel.getLatLng() != null) {
            showProgressDialog(true);
            LatLng currentLatLng = nearbyAirportRequestModel.getLatLng();

            mApiService.getNearbyAirportList(currentLatLng.latitude, currentLatLng.longitude, 100)
                    .enqueue(new Callback<List<NearbyAirportModel>>() {
                        @Override
                        public void onResponse(Call<List<NearbyAirportModel>> call, Response<List<NearbyAirportModel>> response) {
                            // handleSuccessResponse(response);
                            showProgressDialog(false);
                            if (response.body() == null) return;
                            mMutableLiveData.setValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<List<NearbyAirportModel>> call, Throwable t) {
                            handleErrorResponse(t);
                        }
                    });
        }
    }

    public MutableLiveData<List<NearbyAirportModel>> getNearbyAirportResponse() {
        return mMutableLiveData;
    }
}
