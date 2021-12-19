package com.example.squeue.statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.squeue.R;
import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.home.Home;
import com.example.squeue.model.City;
import com.example.squeue.model.District;
import com.example.squeue.model.Ward;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsVaccine extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ImageView ivBack, ivHome;
    private Button btSeeStats;
    private Spinner city, district, ward;
    private List<City> cityList;
    private List<District> districtsList;
    private List<Ward> wardsList;
    private String spinnerCity, spinnerDistrict, spinnerWard;
    private int province_code = 1, district_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_vaccine);
        init();
        setOnClick();
        getAPI();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btSeeStats = findViewById(R.id.btSeeStats);
        city = findViewById(R.id.spinnerCity);
        district = findViewById(R.id.spinnerDistrict);
        ward = findViewById(R.id.spinnerWard);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btSeeStats.setOnClickListener(this);
    }

    public void getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<City>> citiCall = jsonPlaceHolderApi.getCitis();
        Call<List<District>> districtCall = jsonPlaceHolderApi.getDistrict();
        Call<List<Ward>> wardCall = jsonPlaceHolderApi.getWard();

        citiCall.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                cityList = response.body();

                ArrayList<String> listCities = new ArrayList<>();
                for (int i = 0; i < cityList.size(); i++) {
                    listCities.add(cityList.get(i).getName());
                    //Log.i("City", cityList.get(i).getName());
                }

                //lay province code

                for (int i = 0; i < cityList.size(); i++) {
                    // if(cityList.get(i).getName().equals(spinnerCity)){
                    province_code = cityList.get(i).getCode();
                    // }
                }

                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listCities);
                city.setAdapter(cityAdapter);
                city.setOnItemSelectedListener(StatisticsVaccine.this);
            }


            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
            }
        });

        districtCall.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                districtsList = response.body();

                ArrayList<String> listDistrict = new ArrayList<>();
                for (int i = 0; i < districtsList.size(); i++) {
                    // if(districtsList.get(i).getProvince_code()==province_code){
                    listDistrict.add(districtsList.get(i).getName());
                    // }
                }

                //lay district code

                for (int i = 0; i < districtsList.size(); i++) {
                    // if(districtsList.get(i).getName().equals(spinnerDistrict)){
                    district_code = districtsList.get(i).getCode();
                    // }
                }

                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
                district.setAdapter(districtAdapter);
                district.setOnItemSelectedListener(StatisticsVaccine.this);
            }


            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {
            }
        });

        wardCall.enqueue(new Callback<List<Ward>>() {
            @Override
            public void onResponse(Call<List<Ward>> call, Response<List<Ward>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                wardsList = response.body();

                ArrayList<String> listWards = new ArrayList<>();
                for (int i = 0; i < wardsList.size(); i++) {
                    //if(wardsList.get(i).getDistrict_code()==district_code) {
                    listWards.add(wardsList.get(i).getName());
                    //}
                }

                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listWards);
                ward.setAdapter(wardAdapter);
                ward.setOnItemSelectedListener(StatisticsVaccine.this);
            }

            @Override
            public void onFailure(Call<List<Ward>> call, Throwable t) {

            }
        });
    }

    public void vaccineStats() {
        //lay dl tu server ...
        //...

        Intent in = new Intent(this, PieChartVaccine.class);
        in.putExtra("city", spinnerCity);
        in.putExtra("district", spinnerDistrict);
        in.putExtra("ward", spinnerWard);
        startActivity(in);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        spinnerCity = city.getSelectedItem().toString();
        spinnerDistrict = district.getSelectedItem().toString();
        spinnerWard = ward.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btSeeStats.getId()) {
            vaccineStats();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}