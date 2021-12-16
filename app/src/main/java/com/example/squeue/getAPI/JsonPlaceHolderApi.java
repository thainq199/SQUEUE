package com.example.squeue.getAPI;

import com.example.squeue.model.City;
import com.example.squeue.model.District;
import com.example.squeue.model.Ward;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("api/p/")
    Call<List<City>> getCitis();

    @GET("api/d/")
    Call<List<District>> getDistrict();

    @GET("api/w/")
    Call<List<Ward>> getWard();

}
