package com.example.squeue.getAPI;

import com.example.squeue.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("api/p/")
    Call<List<City>> getCitis();
}
