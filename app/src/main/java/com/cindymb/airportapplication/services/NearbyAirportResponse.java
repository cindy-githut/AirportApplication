package com.cindymb.airportapplication.services;


import com.cindymb.airportapplication.base.BaseResponseModel;
import com.cindymb.airportapplication.model.NearbyAirportModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NearbyAirportResponse extends BaseResponseModel {
    @SerializedName("")
    private ArrayList<NearbyAirportModel> data;

    public NearbyAirportResponse() {
    }

    public ArrayList<NearbyAirportModel> getData() {
        return data;
    }

    public void setData(ArrayList<NearbyAirportModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NearbyAirportResponse{" +
                "data=" + data +
                '}';
    }
}
