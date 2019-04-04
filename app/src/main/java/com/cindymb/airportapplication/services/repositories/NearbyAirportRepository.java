package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.ui.nearby.model.NearbyAirportModel;
import com.cindymb.airportapplication.ui.nearby.model.NearbyAirportRequestModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyAirportRepository extends BaseRepository {
    private final MutableLiveData<List<NearbyAirportModel>> mMutableLiveData = new MutableLiveData<>();

    private final ApiService mApiService;

    @Inject
    public NearbyAirportRepository(Application application, ApiService apiService) {
        super(application);
        mApiService = apiService;
    }

    public void getNearbyAirportList(NearbyAirportRequestModel nearbyAirportRequestModel) {
        if (nearbyAirportRequestModel.getLatLng() != null) {
            showProgressDialog(true);
            LatLng currentLatLng = nearbyAirportRequestModel.getLatLng();

            mApiService.getNearbyAirportList(currentLatLng.latitude, currentLatLng.longitude, 100)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(@NotNull Call<Object> call, @NotNull Response<Object> response) {

                            if (response.body() == null) return;
                            String stringGson = new Gson().toJson(response.body());

                            try {
                                Object responseObject = new JSONTokener(stringGson).nextValue();
                                if (responseObject instanceof JSONObject) {

                                    handleJSONObjectResponse(response, stringGson);

                                } else if (responseObject instanceof JSONArray) {

                                    if (handleJSONArrayResponse(response, stringGson) != null && handleJSONArrayResponse(response, stringGson).size() > 0) {
                                        mMutableLiveData.setValue(handleJSONArrayResponse(response, stringGson));
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call<Object> call, @NotNull Throwable t) {
                            handleFailedResponse(t);
                        }
                    });
        }
    }

    public MutableLiveData<List<NearbyAirportModel>> getNearbyAirportResponse() {
        return mMutableLiveData;
    }
}
