package com.cindymb.airportapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirlineModel {

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("iataCode")
    public String iataCode;


    @Expose
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
