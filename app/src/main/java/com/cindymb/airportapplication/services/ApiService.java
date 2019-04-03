package com.cindymb.airportapplication.services;


import com.cindymb.airportapplication.BuildConfig;
import com.cindymb.airportapplication.model.schedule.FlightScheduleModel;
import com.cindymb.airportapplication.model.nearby.NearbyAirportModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Content-Type: application/json"})
    @GET(BuildConfig.NEAR_BY_AIRPORT_URL)
    Call<List<NearbyAirportModel>> getNearbyAirportList(@Query("lat") double lat, @Query("lng") double lng, @Query("distance") int distance);

    @Headers({"Content-Type: application/json"})
    @GET(BuildConfig.AIRPORT_TIME_TABLE_URL)
    Call<List<FlightScheduleModel>> getFlightScheduleList(@Query("iataCode") String iataCode, @Query("type") String type);
}
