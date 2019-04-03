package com.cindymb.airportapplication.model.schedule;

import com.google.gson.annotations.SerializedName;

public class FlightModel {

    @SerializedName("number")
    public String number;

    @SerializedName("iataNumber")
    public String iataNumber;

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
