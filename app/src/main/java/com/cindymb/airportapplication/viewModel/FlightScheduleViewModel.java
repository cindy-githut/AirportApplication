package com.cindymb.airportapplication.viewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cindymb.airportapplication.model.schedule.FlightScheduleModel;
import com.cindymb.airportapplication.services.repositories.FlightScheduleRepository;

import java.util.List;

import javax.inject.Inject;

public class FlightScheduleViewModel extends ViewModel {
    @NonNull
    private final Application mApplication;
    private final MutableLiveData<List<FlightScheduleModel>> mFlightScheduleList = new MutableLiveData<>();
    private final Observer<List<FlightScheduleModel>> response = aFlightScheduleResponse -> {
        if (aFlightScheduleResponse != null && aFlightScheduleResponse.size() > 0) {
            mFlightScheduleList.setValue(aFlightScheduleResponse);
        }
    };
    private FlightScheduleRepository mFlightScheduleRepository;

    @Inject
    FlightScheduleViewModel(@NonNull Application application, FlightScheduleRepository flightScheduleRepository) {

        mApplication = application;
        mFlightScheduleRepository = flightScheduleRepository;
        mFlightScheduleRepository.getFlightDepartureScheduleResponse().observeForever(response);
    }

    public void getFlightScheduleListAPI(String aIATA) {
        mFlightScheduleRepository.getFlightDepartureScheduleList(aIATA);
    }

    public MutableLiveData<List<FlightScheduleModel>> getFlightScheduleList() {
        return mFlightScheduleList;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mFlightScheduleRepository.getFlightDepartureScheduleResponse().removeObserver(response);
    }
}
