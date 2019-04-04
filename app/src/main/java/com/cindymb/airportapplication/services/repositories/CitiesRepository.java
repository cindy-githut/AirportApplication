package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.ui.cities.CitiesModel;
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

public class CitiesRepository extends BaseRepository {

    private final MutableLiveData<List<CitiesModel>> mMutableLiveData = new MutableLiveData<>();
    private final ApiService mApiService;

    @Inject
    public CitiesRepository(Application application, ApiService apiService) {
        super(application);
        mApiService = apiService;
    }

    public void getCityDetails(String codeIataCity) {
        if (!TextUtils.isEmpty(codeIataCity)) {
            showProgressDialog(true);

            mApiService.getFlightCity(codeIataCity)
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

                                    if (handleJSONArrayResponseSchedule(response, stringGson) != null && handleJSONArrayResponseSchedule(response, stringGson).size() > 0) {
                                        mMutableLiveData.setValue(handleJSONArrayResponseCity(response, stringGson));
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

    public MutableLiveData<List<CitiesModel>> getMutableLiveData() {
        return mMutableLiveData;
    }
}
