package com.cindymb.airportapplication.ui.nearby.model;


import com.google.android.gms.maps.model.LatLng;

public class NearbyAirportRequestModel {

    private LatLng LatLng;

    public LatLng getLatLng() {
        return LatLng;
    }

    public void setLatLng(LatLng latLng) {
        LatLng = latLng;
    }
}
