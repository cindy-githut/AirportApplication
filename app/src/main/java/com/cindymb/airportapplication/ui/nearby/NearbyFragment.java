package com.cindymb.airportapplication.ui.nearby;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.databinding.FragmentNearbyBinding;
import com.cindymb.airportapplication.di.MyViewModelFactory;
import com.cindymb.airportapplication.ui.base.BaseFragment;
import com.cindymb.airportapplication.ui.nearby.model.NearbyAirportModel;
import com.cindymb.airportapplication.ui.nearby.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.ui.utils.ConnectionEventBus;
import com.cindymb.airportapplication.ui.utils.MyUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class NearbyFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    @Inject
    MyViewModelFactory mFactory;
    @Inject
    NearbyAirportRequestModel mNearbyAirportRequestModel;

    private GoogleMap mGoogleMap;
    private LatLng mCurrentLatLng;
    private NearbyAirportViewModel mNearbyAirportViewModel;
    private List<NearbyAirportModel> mNearbyAirportModelList = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ConnectionEventBus.ConnectedEvent event) {
        if (event.getConnected()) {
            getCurrentLocation();
        } else {
            displayDialog(getString(R.string.msg_google_api_error));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentNearbyBinding aFragmentNearbyBinding = FragmentNearbyBinding.inflate(inflater, container, false);
        aFragmentNearbyBinding.mapView.onCreate(savedInstanceState);
        aFragmentNearbyBinding.mapView.onResume();

        if (isConnected(requireActivity())) {
            aFragmentNearbyBinding.mapView.getMapAsync(this);

            if (MyUtils.isFirstTimeLogin) {
                EventBus.getDefault().postSticky(new ConnectionEventBus.ConnectedEvent(true));
                MyUtils.isFirstTimeLogin = false;
            }

        } else {
            displayDialog(getString(R.string.msg_connectionError));
        }
        return aFragmentNearbyBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNearbyAirportViewModel = ViewModelProviders.of(this, mFactory).get(NearbyAirportViewModel.class);

        mNearbyAirportViewModel.getNearbyAirportList().observe(this, nearbyAirportList -> {
            if (nearbyAirportList != null && nearbyAirportList.size() > 0) {
                mNearbyAirportModelList = nearbyAirportList;
                mNearbyAirportViewModel.plotAirportsOnMap(mNearbyAirportModelList, mGoogleMap);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleMap != null) {
            getCurrentLocation();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @AfterPermissionGranted(MyUtils.PERMISSION_CODE)
    private void getCurrentLocation() {
        if (EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {

            FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
            try {

                mFusedLocationClient.getLastLocation().addOnSuccessListener(requireActivity(), location -> {
                    if (location != null) {
                        mCurrentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                        moveMap();
                    } else {
                        displayDialog(getString(R.string.msg_location_error));
                    }
                });

            } catch (SecurityException sec) {
                displayDialog(sec.getMessage());
            }
        } else {
            EasyPermissions.requestPermissions(requireActivity(), getString(R.string.msg_permission_request), MyUtils.PERMISSION_CODE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }

    private void moveMap() {
        mGoogleMap.clear();
        mGoogleMap.addMarker(new MarkerOptions()
                .position(mCurrentLatLng));

        try {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mCurrentLatLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
            mGoogleMap.setMyLocationEnabled(true);

            mNearbyAirportRequestModel.setLatLng(mCurrentLatLng);
            if (isConnected(requireActivity())) {
                if (mNearbyAirportModelList.size() == 0) {
                    mNearbyAirportViewModel.getNearbyAirportListAPI(mNearbyAirportRequestModel);
                } else {
                    mNearbyAirportViewModel.plotAirportsOnMap(mNearbyAirportModelList, mGoogleMap);
                }
            } else {
                displayDialog(getString(R.string.msg_connectionError));
            }

            mGoogleMap.setOnMarkerClickListener(this);

        } catch (SecurityException sec) {
            displayDialog(sec.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        //this is to focus the map on Gauteng when loading the map, changes after getting the users current location
        mGoogleMap.addMarker(new MarkerOptions().position(GAUTENG_COORDINATES));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(GAUTENG_COORDINATES));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        for (NearbyAirportModel aNearbyAirportModel : mNearbyAirportViewModel.getAirportListPloted()) {

            LatLng plotedAirportLatLng = new LatLng(Double.parseDouble(aNearbyAirportModel.getLatitudeAirport()), Double.parseDouble(aNearbyAirportModel.getLongitudeAirport()));

            if (marker.getPosition().equals(plotedAirportLatLng)) {
                if (!TextUtils.isEmpty(aNearbyAirportModel.getCodeIataAirport())) {

                    NearbyFragmentDirections.ActionMapsFragmentToFlightScheduleFragment action =
                            NearbyFragmentDirections.actionMapsFragmentToFlightScheduleFragment(aNearbyAirportModel.getCodeIataAirport(), aNearbyAirportModel);

                    navigateToNextScreenWithArguments(action);
                    aNearbyAirportModel.setNameAirport(aNearbyAirportModel.nameAirport);

                } else {

                    displayDialog(getString(R.string.msg_iata_error));

                }
                break;
            }
        }
        return true;
    }
}
