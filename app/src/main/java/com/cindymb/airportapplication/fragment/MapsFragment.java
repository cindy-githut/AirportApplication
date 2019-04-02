package com.cindymb.airportapplication.fragment;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.base.BaseFragment;
import com.cindymb.airportapplication.databinding.FragmentMapsBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.utils.Constant;
import com.cindymb.airportapplication.viewModel.NearbyAirportViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MapsFragment extends BaseFragment implements OnMapReadyCallback {

    @Inject
    MyViewModelFactory mFactory;
    @Inject
    NearbyAirportRequestModel mNearbyAirportRequestModel;

    private GoogleMap mGoogleMap;
    private LatLng mCurrentLatLng;
    private NearbyAirportViewModel mNearbyAirportViewModel;
    private FragmentMapsBinding mFragmentMapsBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentMapsBinding = FragmentMapsBinding.inflate(inflater, container, false);
        mFragmentMapsBinding.mapView.onCreate(savedInstanceState);
        mFragmentMapsBinding.mapView.onResume();

        if (isConnected(requireActivity())) {
            mFragmentMapsBinding.mapView.getMapAsync(this);
        } else {
            displayDialog(getString(R.string.lbl_connectionError));
        }
        return mFragmentMapsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNearbyAirportViewModel = ViewModelProviders.of(this, mFactory).get(NearbyAirportViewModel.class);

        mNearbyAirportViewModel.getNearbyAirportList().observe(this, nearbyAirportList -> {
            if (nearbyAirportList != null && nearbyAirportList.size() > 0) {
                mNearbyAirportViewModel.plotAirportsOnMap(nearbyAirportList, mGoogleMap);
            }
        });
    }

    @AfterPermissionGranted(Constant.PERMISSION_CODE)
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            if (mGoogleMap == null) {
                mGoogleMap = googleMap;
                mGoogleMap.setMyLocationEnabled(true);
            }
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.getUiSettings().setRotateGesturesEnabled(false);

            if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

                mFusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), location -> {

                    try {
                        if (location != null) {
                            mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                            googleMap.clear();
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLatLng, 14.0f));
                            googleMap.getUiSettings().setZoomControlsEnabled(false);

                            if (mCurrentLatLng != null) {
                                mNearbyAirportRequestModel.setLatLng(mCurrentLatLng);
                                if (isConnected(requireActivity())) {
                                    mNearbyAirportViewModel.getNearbyAirportListAPI(mNearbyAirportRequestModel);
                                } else {
                                    displayDialog(getString(R.string.lbl_connectionError));
                                }
                            }
                        } else {
                            displayDialog(getString(R.string.msg_location_error));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        displayDialog(String.format("%s %s", getString(R.string.lbl_map_error), e.getMessage()));
                    }
                });
            } else {
                EasyPermissions.requestPermissions(requireActivity(), getString(R.string.msg_permission_request), Constant.PERMISSION_CODE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
            }

        } catch (SecurityException exception) {
            exception.printStackTrace();
            displayDialog(String.format("%s %s", getString(R.string.lbl_map_error), exception.getMessage()));
        }
    }
}
