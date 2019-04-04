package com.cindymb.airportapplication.ui.schedule;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.databinding.FragmentFlightScheduleBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.ui.base.BaseFragment;
import com.cindymb.airportapplication.ui.utils.RecyclerViewConfig;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;


public class FlightScheduleFragment extends BaseFragment {

    @Inject
    MyViewModelFactory mFactory;
    FlightScheduleViewModel mFlightScheduleViewModel;
    private FlightScheduleAdapter mFlightScheduleAdapter;
    private FragmentFlightScheduleBinding mFragmentFlightScheduleBinding;
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
        mFragmentFlightScheduleBinding = FragmentFlightScheduleBinding.inflate(inflater, container, false);
        mFlightScheduleAdapter = new FlightScheduleAdapter();
        assert getArguments() != null;
        mIATA = FlightScheduleFragmentArgs.fromBundle(getArguments()).getIataCode();
        ((AppCompatActivity) requireActivity()).setSupportActionBar(mFragmentFlightScheduleBinding.toolbar);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String itemTitle = "ME ME ME ME ME ME ME ME  ME ME ME MEM E";
        mFragmentFlightScheduleBinding.collapsingToolbar.setTitle(itemTitle);

        return mFragmentFlightScheduleBinding.getRoot();
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

        mFragmentFlightScheduleBinding.setRecyclerView(mRecyclerView);
        mRecyclerView.setConfig(new LinearLayoutManager(requireContext()), mFlightScheduleAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                requireActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
