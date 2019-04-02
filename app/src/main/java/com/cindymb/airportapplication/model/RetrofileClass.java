package com.cindymb.airportapplication.model;

import com.cindymb.airportapplication.services.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofileClass {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://aviation-edge.com/api/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService service = retrofit.create(ApiService.class);
}
