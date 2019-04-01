package com.cindymb.airportapplication.services;


import com.cindymb.airportapplication.model.NearbyAirportModel;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NearbyAirportResponse {
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
