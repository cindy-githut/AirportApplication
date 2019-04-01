package com.cindymb.airportapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightModel {
    @Expose
    @SerializedName("number")
    public String number;

    @Expose
    @SerializedName("iataNumber")
    public String iataNumber;

    @Expose
    @SerializedName("icaoNumber")
    public String icaoNumber;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIataNumber() {
        return iataNumber;
    }

    public void setIataNumber(String iataNumber) {
        this.iataNumber = iataNumber;
    }

    public String getIcaoNumber() {
        return icaoNumber;
    }

    public void setIcaoNumber(String icaoNumber) {
        this.icaoNumber = icaoNumber;
    }
}
