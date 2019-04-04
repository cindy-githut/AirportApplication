package com.cindymb.airportapplication.ui.schedule.model;

import com.google.gson.annotations.SerializedName;

public class ArrivalModel {

    @SerializedName("iataCode")
    public String iataCode;

    @SerializedName("icaoCode")
    public String icaoCode;

    @SerializedName("scheduledTime")
    public String scheduledTime;

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

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
