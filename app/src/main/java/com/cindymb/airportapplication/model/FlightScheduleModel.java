package com.cindymb.airportapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightScheduleModel {

    @Expose
    @SerializedName("type")
    public String type;
    @Expose
    @SerializedName("status")
    public String status;

}
