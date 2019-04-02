package com.cindymb.airportapplication.services;


import com.cindymb.airportapplication.model.schedule.FlightScheduleModel;
import com.cindymb.airportapplication.model.nearby.NearbyAirportModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers({"Content-Type: application/json"})
    //@GET("nearby?key=" + BuildConfig.APP_AIRPORT_API_KEY)
    @GET("getall")
    Call<List<NearbyAirportModel>> getNearbyAirportList(@Query("lat") double lat, @Query("lng") double lng, @Query("distance") int distance);

    @Headers({"Content-Type: application/json"})
    // @GET("timetable?key=" + BuildConfig.APP_AIRPORT_API_KEY)
    @GET("timetable")
    Call<List<FlightScheduleModel>> getFlightScheduleList(@Query("iataCode") String iataCode, @Query("type") String type);
}
