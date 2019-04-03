package com.cindymb.airportapplication.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.adapter.FlightScheduleAdapter;
import com.cindymb.airportapplication.base.BaseFragment;
import com.cindymb.airportapplication.databinding.AirplaneDepartureBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.utils.RecyclerViewConfig;
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
    private RecyclerViewConfig mRecyclerView = new RecyclerViewConfig();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAirplaneDepartureBinding = AirplaneDepartureBinding.inflate(inflater, container, false);
        mFlightScheduleAdapter = new FlightScheduleAdapter();
        assert getArguments() != null;
        mIATA = FlightScheduleFragmentArgs.fromBundle(getArguments()).getIataCode();
        ((AppCompatActivity)getActivity()).setSupportActionBar( mAirplaneDepartureBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "ME ME ME ME ME ME ME ME  ME ME ME MEM E";
        mAirplaneDepartureBinding.collapsingToolbar.setTitle(itemTitle);

        return mAirplaneDepartureBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFlightScheduleViewModel = ViewModelProviders.of(this, mFactory).get(FlightScheduleViewModel.class);
        if (isConnected(requireActivity())) {
            mFlightScheduleViewModel.getFlightScheduleListAPI(mIATA);
        } else {
            displayDialog(getString(R.string.msg_connectionError));
        }
        mFlightScheduleViewModel.getFlightScheduleList().observe(this, flightScheduleList -> {
            if (flightScheduleList != null && flightScheduleList.size() > 0) {
                mFlightScheduleAdapter.setFlightScheduleModels(flightScheduleList);
            }
        });

        mAirplaneDepartureBinding.setRecyclerView(mRecyclerView);
        mRecyclerView.setConfig(new LinearLayoutManager(requireContext()), mFlightScheduleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
