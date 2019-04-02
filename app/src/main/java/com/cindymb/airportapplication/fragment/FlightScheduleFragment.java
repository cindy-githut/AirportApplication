package com.cindymb.airportapplication.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.adapter.FlightScheduleAdapter;
import com.cindymb.airportapplication.base.BaseFragment;
import com.cindymb.airportapplication.databinding.AirplaneDepartureBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.viewModel.FlightScheduleViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


public class FlightScheduleFragment extends BaseFragment {

    @Inject
    MyViewModelFactory mFactory;
    FlightScheduleViewModel mFlightScheduleViewModel;
    private FlightScheduleAdapter mFlightScheduleAdapter;
    private AirplaneDepartureBinding mAirplaneDepartureBinding;
    private String mIATA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAirplaneDepartureBinding = AirplaneDepartureBinding.inflate(inflater, container, false);
        mFlightScheduleAdapter = new FlightScheduleAdapter();
        FlightScheduleFragmentArgs aFlightScheduleFragmentArgs = FlightScheduleFragmentArgs.fromBundle(getArguments());
        mIATA = aFlightScheduleFragmentArgs.getIataCode();
        return mAirplaneDepartureBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFlightScheduleViewModel = ViewModelProviders.of(this, mFactory).get(FlightScheduleViewModel.class);
        if (isConnected(requireActivity())) {
            mFlightScheduleViewModel.getFlightScheduleListAPI(mIATA);
        } else {
            displayDialog(getString(R.string.lbl_connectionError));
        }
        mFlightScheduleViewModel.getFlightScheduleList().observe(this, flightScheduleList -> {
            if (flightScheduleList != null && flightScheduleList.size() > 0) {
                //continue set an adapter
                mFlightScheduleAdapter.setFlightScheduleModels(flightScheduleList);
            }
        });
        mAirplaneDepartureBinding.scheduleRecyclerView.setAdapter(mFlightScheduleAdapter);
    }

}
