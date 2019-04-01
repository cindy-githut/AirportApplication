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

    @Expose
    @SerializedName("departure")
    public DepartureModel departureModel;

    @Expose
    @SerializedName("airline")
    public AirlineModel airlineModel;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DepartureModel getDepartureModel() {
        return departureModel;
    }

    public void setDepartureModel(DepartureModel departureModel) {
        this.departureModel = departureModel;
    }

    public AirlineModel getAirlineModel() {
        return airlineModel;
    }

    public void setAirlineModel(AirlineModel airlineModel) {
        this.airlineModel = airlineModel;
    }
}
