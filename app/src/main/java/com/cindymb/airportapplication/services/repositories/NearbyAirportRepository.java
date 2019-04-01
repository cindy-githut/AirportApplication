package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.cindymb.airportapplication.base.BaseRepository;
import com.cindymb.airportapplication.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.services.NearbyAirportResponse;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyAirportRepository extends BaseRepository {
    private final MutableLiveData<NearbyAirportResponse> mMutableLiveData = new MutableLiveData<>();

    private final Application mApplication;
    private final ApiService mApiService;

    @Inject
    public NearbyAirportRepository(Application application, ApiService apiService) {
        super(application);
        mApplication = application;
        mApiService = apiService;
    }

    public void getNearbyAirportList(NearbyAirportRequestModel nearbyAirportRequestModel) {
        if (nearbyAirportRequestModel != null) {
            showProgressDialog(true);
            LatLng currentLatLng = nearbyAirportRequestModel.getLatLng();
            mApiService.getNearbyAirportList(currentLatLng.latitude, currentLatLng.longitude, 100)
                    .enqueue(new Callback<NearbyAirportResponse>() {
                        @Override
                        public void onResponse(Call<NearbyAirportResponse> call, Response<NearbyAirportResponse> response) {
                            handleSuccessResponse(response);
                            if (response.body() == null) return;
                            mMutableLiveData.setValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<NearbyAirportResponse> call, Throwable t) {
                            handleErrorResponse(t);
                        }
                    });
        } else {
            //TODO
            //display currentLatLng Error
        }
    }

    public MutableLiveData<NearbyAirportResponse> getNearbyAirportResponse() {
        return mMutableLiveData;
    }
}
