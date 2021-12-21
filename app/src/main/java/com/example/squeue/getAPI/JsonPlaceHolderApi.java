package com.example.squeue.getAPI;

import com.example.squeue.model.City;
import com.example.squeue.model.District;
import com.example.squeue.model.Phuong;
import com.example.squeue.model.Qr;
import com.example.squeue.model.Quan;
import com.example.squeue.model.Todanpho;
import com.example.squeue.model.Token;
import com.example.squeue.model.Vaccine;
import com.example.squeue.model.Ward;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("api/p/")
    Call<List<City>> getCitis();

    @GET("api/d/")
    Call<List<District>> getDistrict();

    @GET("api/w/")
    Call<List<Ward>> getWard();

    @POST("admins/login")
    Call<Token> login(@Body Token token);

    @GET("/vacxins")
    Call<List<Vaccine>> getVaccine();

    @GET("/phuongs")
    Call<List<Quan>> getQuan();

    @GET("/quans")
    Call<List<Phuong>> getPhuong();

    @GET("/todanphos")
    Call<List<Todanpho>> getTodanpho();

    @POST("qr")
    Call<Qr> createQr(@Body String qr);
}
