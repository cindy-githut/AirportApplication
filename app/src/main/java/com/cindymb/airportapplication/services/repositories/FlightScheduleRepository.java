package com.cindymb.airportapplication.services.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.cindymb.airportapplication.base.BaseRepository;
import com.cindymb.airportapplication.model.schedule.FlightScheduleModel;
import com.cindymb.airportapplication.services.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightScheduleRepository extends BaseRepository {
    private final MutableLiveData<List<FlightScheduleModel>> mMutableLiveData = new MutableLiveData<>();

    private final Application mApplication;
    private final ApiService mApiService;


    @Inject
    public FlightScheduleRepository(Application application, ApiService apiService) {
        super(application);
        mApplication = application;
        mApiService = apiService;
    }

    public void getFlightDepartureScheduleList(String aIATA) {
        if (!TextUtils.isEmpty(aIATA)) {
            showProgressDialog(true);

            mApiService.getFlightScheduleList(aIATA, "departure")
                    .enqueue(new Callback<List<FlightScheduleModel>>() {
                        @Override
                        public void onResponse(Call<List<FlightScheduleModel>> call, Response<List<FlightScheduleModel>> response) {
                            // handleSuccessResponse(response);
                            showProgressDialog(false);
                            if (response.body() == null) return;
                            mMutableLiveData.setValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<List<FlightScheduleModel>> call, Throwable t) {
                            handleErrorResponse(t);
                        }
                    });
        }
    }

    public MutableLiveData<List<FlightScheduleModel>> getFlightDepartureScheduleResponse() {
        return mMutableLiveData;
    }
}
