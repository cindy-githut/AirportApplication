package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.ui.schedule.model.FlightScheduleModel;
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

public class FlightScheduleRepository extends BaseRepository {
    private final MutableLiveData<List<FlightScheduleModel>> mMutableLiveData = new MutableLiveData<>();

    private final ApiService mApiService;


    @Inject
    public FlightScheduleRepository(Application application, ApiService apiService) {
        super(application);
        mApiService = apiService;
    }

    public void getFlightDepartureScheduleList(String aIATA) {
        if (!TextUtils.isEmpty(aIATA)) {
            showProgressDialog(true);

            mApiService.getFlightScheduleList(aIATA, "departure")
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
                                        mMutableLiveData.setValue(handleJSONArrayResponseSchedule(response, stringGson));
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

    public MutableLiveData<List<FlightScheduleModel>> getFlightDepartureScheduleResponse() {
        return mMutableLiveData;
    }
}
