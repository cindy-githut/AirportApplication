package com.cindymb.airportapplication.ui.schedule;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import com.cindymb.airportapplication.services.repositories.FlightScheduleRepository;
import com.cindymb.airportapplication.ui.schedule.model.FlightScheduleModel;

import java.util.List;

import javax.inject.Inject;

public class FlightScheduleViewModel extends ViewModel {
    private final MutableLiveData<List<FlightScheduleModel>> mFlightScheduleList = new MutableLiveData<>();
    private final Observer<List<FlightScheduleModel>> response = aFlightScheduleResponse -> {
        if (aFlightScheduleResponse != null && aFlightScheduleResponse.size() > 0) {
            mFlightScheduleList.setValue(aFlightScheduleResponse);
        }
    };
    private FlightScheduleRepository mFlightScheduleRepository;

    @Inject
    FlightScheduleViewModel(FlightScheduleRepository flightScheduleRepository) {

        mFlightScheduleRepository = flightScheduleRepository;
        mFlightScheduleRepository.getFlightDepartureScheduleResponse().observeForever(response);
    }

    void getFlightScheduleListAPI(String aIATA) {
        mFlightScheduleRepository.getFlightDepartureScheduleList(aIATA);
    }

    MutableLiveData<List<FlightScheduleModel>> getFlightScheduleList() {
        return mFlightScheduleList;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mFlightScheduleRepository.getFlightDepartureScheduleResponse().removeObserver(response);
    }
}
