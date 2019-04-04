package com.cindymb.airportapplication.ui.schedule.model;

import com.google.gson.annotations.SerializedName;

public class AirlineModel {

    @SerializedName("name")
    public String name;

    @SerializedName("iataCode")
    public String iataCode;

    @SerializedName("icaoCode")
    public String icaoCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }
}
