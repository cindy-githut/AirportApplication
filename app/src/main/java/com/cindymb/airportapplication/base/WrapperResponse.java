package com.cindymb.airportapplication.base;

import com.cindymb.airportapplication.model.nearby.NearbyAirportModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WrapperResponse {

    List<NearbyAirportModel> res;

    @SerializedName("Error")
    private ErrorText error;

    public List<NearbyAirportModel> getRes() {
        return res;
    }

    public void setRes(List<NearbyAirportModel> res) {
        this.res = res;
    }

    public ErrorText getError() {
        return error;
    }

    public void setError(ErrorText error) {
        this.error = error;
    }
}
