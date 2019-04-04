package com.cindymb.airportapplication.ui.schedule.model;

import com.google.gson.annotations.SerializedName;

public class FlightScheduleModel {

    @SerializedName("type")
    public String type;

    @SerializedName("status")
    public String status;

    @SerializedName("departure")
    public DepartureModel departureModel;

    @SerializedName("arrival")
    public ArrivalModel arrivalModel;

    @SerializedName("airline")
    public AirlineModel airlineModel;

    @SerializedName("flight")
    public FlightModel flightModel;


    public FlightModel getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(FlightModel flightModel) {
        this.flightModel = flightModel;
    }

    public ArrivalModel getArrivalModel() {
        return arrivalModel;
    }

    public void setArrivalModel(ArrivalModel arrival) {
        this.arrivalModel = arrival;
    }

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
