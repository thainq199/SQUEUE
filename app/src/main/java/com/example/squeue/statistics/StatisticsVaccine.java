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
import com.example.squeue.model.Phuong;
import com.example.squeue.model.Quan;
import com.example.squeue.model.Todanpho;
import com.example.squeue.model.Vaccine;
import com.example.squeue.model.Ward;
import com.example.squeue.qr.QRCode;

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
    private Spinner city, district, ward, todanpho;
    private List<City> cityList;
    private List<Quan> districtsList;
    private List<Phuong> wardsList;
    private List<Todanpho> todanphoList;
    private String spinnerCity, spinnerDistrict, spinnerWard,spinnertoDanPho;
    private int province_code = 1, district_code = 1, todanpho_id;

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
        todanpho = findViewById(R.id.spinnerTodanpho);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btSeeStats.setOnClickListener(this);
    }

//    public void getAPI() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://provinces.open-api.vn/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//        Call<List<City>> citiCall = jsonPlaceHolderApi.getCitis();
//        Call<List<District>> districtCall = jsonPlaceHolderApi.getDistrict();
//        Call<List<Ward>> wardCall = jsonPlaceHolderApi.getWard();
//
//        citiCall.enqueue(new Callback<List<City>>() {
//            @Override
//            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
//
//                if (!response.isSuccessful()) {
//                    return;
//                }
//
//                cityList = response.body();
//
//                ArrayList<String> listCities = new ArrayList<>();
//                for (int i = 0; i < cityList.size(); i++) {
//                    listCities.add(cityList.get(i).getName());
//                    //Log.i("City", cityList.get(i).getName());
//                }
//
//                //lay province code
//
//                for (int i = 0; i < cityList.size(); i++) {
//                    // if(cityList.get(i).getName().equals(spinnerCity)){
//                    province_code = cityList.get(i).getCode();
//                    // }
//                }
//
//                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listCities);
//                city.setAdapter(cityAdapter);
//                city.setOnItemSelectedListener(StatisticsVaccine.this);
//            }
//
//
//            @Override
//            public void onFailure(Call<List<City>> call, Throwable t) {
//            }
//        });
//
//        districtCall.enqueue(new Callback<List<District>>() {
//            @Override
//            public void onResponse(Call<List<District>> call, Response<List<District>> response) {
//
//                if (!response.isSuccessful()) {
//                    return;
//                }
//
//                districtsList = response.body();
//
//                ArrayList<String> listDistrict = new ArrayList<>();
//                for (int i = 0; i < districtsList.size(); i++) {
//                    // if(districtsList.get(i).getProvince_code()==province_code){
//                    listDistrict.add(districtsList.get(i).getName());
//                    // }
//                }
//
//                //lay district code
//
//                for (int i = 0; i < districtsList.size(); i++) {
//                    // if(districtsList.get(i).getName().equals(spinnerDistrict)){
//                    district_code = districtsList.get(i).getCode();
//                    // }
//                }
//
//                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
//                district.setAdapter(districtAdapter);
//                district.setOnItemSelectedListener(StatisticsVaccine.this);
//            }
//
//
//            @Override
//            public void onFailure(Call<List<District>> call, Throwable t) {
//            }
//        });
//
//        wardCall.enqueue(new Callback<List<Ward>>() {
//            @Override
//            public void onResponse(Call<List<Ward>> call, Response<List<Ward>> response) {
//
//                if (!response.isSuccessful()) {
//                    return;
//                }
//
//                wardsList = response.body();
//
//                ArrayList<String> listWards = new ArrayList<>();
//                for (int i = 0; i < wardsList.size(); i++) {
//                    //if(wardsList.get(i).getDistrict_code()==district_code) {
//                    listWards.add(wardsList.get(i).getName());
//                    //}
//                }
//
//                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listWards);
//                ward.setAdapter(wardAdapter);
//                ward.setOnItemSelectedListener(StatisticsVaccine.this);
//            }
//
//            @Override
//            public void onFailure(Call<List<Ward>> call, Throwable t) {
//
//            }
//        });
//    }

    public void getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.130:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Quan>> districtCall = jsonPlaceHolderApi.getQuan();
        Call<List<Phuong>> wardCall = jsonPlaceHolderApi.getPhuong();
        Call<List<Todanpho>> TodanphoCall = jsonPlaceHolderApi.getTodanpho();

        districtCall.enqueue(new Callback<List<Quan>>() {
            @Override
            public void onResponse(Call<List<Quan>> call, Response<List<Quan>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                districtsList = response.body();

                ArrayList<String> listDistrict = new ArrayList<>();
                for (int i = 0; i < districtsList.size(); i++) {
                    // if(districtsList.get(i).getProvince_code()==province_code){
                    listDistrict.add(districtsList.get(i).getTen());
                    // }
                }

                //lay district code

                for (int i = 0; i < districtsList.size(); i++) {
                    // if(districtsList.get(i).getName().equals(spinnerDistrict)){
                    district_code = districtsList.get(i).getId();
                    // }
                }

                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
                district.setAdapter(districtAdapter);
                district.setOnItemSelectedListener(StatisticsVaccine.this);
            }


            @Override
            public void onFailure(Call<List<Quan>> call, Throwable t) {
            }
        });

        wardCall.enqueue(new Callback<List<Phuong>>() {
            @Override
            public void onResponse(Call<List<Phuong>> call, Response<List<Phuong>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                wardsList = response.body();

                ArrayList<String> listWards = new ArrayList<>();
                for (int i = 0; i < wardsList.size(); i++) {
                    //if(wardsList.get(i).getDistrict_code()==district_code) {
                    listWards.add(wardsList.get(i).getTen());
                    //}
                }

                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listWards);
                ward.setAdapter(wardAdapter);
                ward.setOnItemSelectedListener(StatisticsVaccine.this);
            }


            @Override
            public void onFailure(Call<List<Phuong>> call, Throwable t) {

            }
        });

        TodanphoCall.enqueue(new Callback<List<Todanpho>>() {
            @Override
            public void onResponse(Call<List<Todanpho>> call, Response<List<Todanpho>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                todanphoList = response.body();

                ArrayList<String> listTodanpho = new ArrayList<>();
                for (int i = 0; i < todanphoList.size(); i++) {
                    //if(wardsList.get(i).getDistrict_code()==district_code) {
                    listTodanpho.add(todanphoList.get(i).getTen());
                    //}
                }

                ArrayAdapter<String> todanphoAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listTodanpho);
                todanpho.setAdapter(todanphoAdapter);
                todanpho.setOnItemSelectedListener(StatisticsVaccine.this);
            }


            @Override
            public void onFailure(Call<List<Todanpho>> call, Throwable t) {

            }
        });

        ArrayList<String> listCity = new ArrayList<>();
        listCity.add("Thành phố Hà Nội");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(StatisticsVaccine.this, android.R.layout.simple_spinner_dropdown_item, listCity);
        city.setAdapter(cityAdapter);
        city.setOnItemSelectedListener(StatisticsVaccine.this);
    }

    public void vaccineStats() {
        //lay dl tu server ...
        //...

        Intent in = new Intent(this, PieChartVaccine.class);
        in.putExtra("city", spinnerCity);
        in.putExtra("district", spinnerDistrict);
        in.putExtra("ward", spinnerWard);
        in.putExtra("todanpho", spinnertoDanPho);
        in.putExtra("todanpho_id", todanpho_id);
        startActivity(in);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        spinnerCity = city.getSelectedItem().toString();
        spinnerDistrict = district.getSelectedItem().toString();
        spinnerWard = ward.getSelectedItem().toString();
        spinnertoDanPho = todanpho.getSelectedItem().toString();
        for (int i = 0; i < todanphoList.size(); i++) {
            if(todanphoList.get(i).getTen().equals(spinnertoDanPho)){
                todanpho_id=todanphoList.get(i).getId();
            }
        }
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