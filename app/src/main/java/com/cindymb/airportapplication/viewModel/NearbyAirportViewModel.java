package com.cindymb.airportapplication.viewModel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.cindymb.airportapplication.R;
import com.cindymb.airportapplication.model.nearby.NearbyAirportModel;
import com.cindymb.airportapplication.model.nearby.NearbyAirportRequestModel;
import com.cindymb.airportapplication.services.repositories.NearbyAirportRepository;
import com.cindymb.airportapplication.utils.LoggingHelper;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.cindymb.airportapplication.base.BaseFragment.getMarkerIconFromDrawable;

public class NearbyAirportViewModel extends ViewModel {
    @NonNull
    private final Application mApplication;
    private final MutableLiveData<List<NearbyAirportModel>> mNearbyAirportList = new MutableLiveData<>();
    private final Observer<List<NearbyAirportModel>> response = aNearbyAirportResponse -> {
        if (aNearbyAirportResponse != null && aNearbyAirportResponse.size() > 0) {
            mNearbyAirportList.setValue(aNearbyAirportResponse);
        }
    };
    @Inject
    NearbyAirportRequestModel mNearbyAirportRequestModel;
    List<NearbyAirportModel> mAirportPlotPoint = new ArrayList<>();
    private NearbyAirportRepository mNearbyAirportRepository;

    @Inject
    NearbyAirportViewModel(@NonNull Application application, NearbyAirportRepository nearbyAirportRepository) {

        mApplication = application;
        mNearbyAirportRepository = nearbyAirportRepository;
        mNearbyAirportRepository.getNearbyAirportResponse().observeForever(response);
    }

    public void getNearbyAirportListAPI(NearbyAirportRequestModel nearbyAirportRequestModel) {
        mNearbyAirportRepository.getNearbyAirportList(nearbyAirportRequestModel);
    }

    public MutableLiveData<List<NearbyAirportModel>> getNearbyAirportList() {
        return mNearbyAirportList;
    }

    public List<NearbyAirportModel> getAirportListPloted() {
        return mAirportPlotPoint;
    }

    public void plotAirportsOnMap(List<NearbyAirportModel> airportModelList, GoogleMap aGoogleMap) {

        List<NearbyAirportModel> airportPlotPoint = new ArrayList<>();

        for (NearbyAirportModel aNearbyAirportModel : airportModelList) {
            if (!TextUtils.isEmpty(aNearbyAirportModel.getLatitudeAirport()) && Double.parseDouble(aNearbyAirportModel.getLatitudeAirport()) != 0
                    && !TextUtils.isEmpty(aNearbyAirportModel.getLongitudeAirport()) && Double.parseDouble(aNearbyAirportModel.getLongitudeAirport()) != 0) {
                airportPlotPoint.add(aNearbyAirportModel);
            }
        }
        mAirportPlotPoint = airportPlotPoint;

        if (airportPlotPoint.size() > 0) {
            plotAirportPoint(airportPlotPoint, aGoogleMap);
        }
    }

    private void plotAirportPoint(List<NearbyAirportModel> airportPlotPointList, GoogleMap aGoogleMap) {
        if (aGoogleMap != null) {

            try {
                aGoogleMap.clear();
                aGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                ArrayList<Marker> markers = new ArrayList<>();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                Drawable mapIcon = mApplication.getResources().getDrawable(R.drawable.ic_pin);
                BitmapDescriptor marker_Icon = getMarkerIconFromDrawable(mapIcon);
                int padding;

                for (NearbyAirportModel aNearbyAirportModel : airportPlotPointList) {
                    LatLng airportLatLng = new LatLng(Double.parseDouble(aNearbyAirportModel.getLatitudeAirport()), Double.parseDouble(aNearbyAirportModel.getLongitudeAirport()));
                    MarkerOptions markerOptions = new MarkerOptions().position(airportLatLng);
                    markerOptions.icon(marker_Icon);
                    markerOptions.flat(true);
                    builder.include(airportLatLng);
                    markers.add(aGoogleMap.addMarker(markerOptions));
                }
                LatLngBounds bounds = builder.build();

                int width = mApplication.getResources().getDisplayMetrics().widthPixels;
                padding = (int) (width * 0.20);
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                aGoogleMap.animateCamera(cu);

                // focus on users current location
                CameraUpdate center = CameraUpdateFactory.newLatLng(mNearbyAirportRequestModel.getLatLng());
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
                aGoogleMap.moveCamera(center);
                aGoogleMap.animateCamera(zoom);

            } catch (Exception ex) {
                LoggingHelper.error(NearbyAirportViewModel.class, ex.getMessage());
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mNearbyAirportRepository.getNearbyAirportResponse().removeObserver(response);
    }
}
