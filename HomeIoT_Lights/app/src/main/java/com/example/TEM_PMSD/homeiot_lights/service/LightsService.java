package com.example.TEM_PMSD.homeiot_lights.service;

import com.example.TEM_PMSD.homeiot_lights.model.Lights;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LightsService {
    @GET("/lights/list")
    Call<List<Lights>>getLightList();

    @POST("/lights/new")
    Call<Lights> createLights(@Body Lights lights);

    @POST("/lights/update")
    Call<Lights> updateLight(@Body Lights lights);

//    @POST("/lights/delete")

}
