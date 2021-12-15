package com.example.squeue.qr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;
import com.example.squeue.model.City;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QRCode extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ImageView ivBack, ivHome;
    private Button btGenQR;
    private Spinner city, district, ward;
    private TextView textViewResult;
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        init();
        setOnClick();
        getAPI(1);
        getAPI(2);
        getAPI(3);
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btGenQR = findViewById(R.id.btGenQr);
        city = findViewById(R.id.spinnerCity);
        district = findViewById(R.id.spinnertvDistrict);
        ward = findViewById(R.id.spinnerWard);
        textViewResult=findViewById(R.id.textViewResult);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btGenQR.setOnClickListener(this);
    }

    public void getAPI(int choose) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<City>> call = jsonPlaceHolderApi.getCitis();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                cityList = response.body();

//                for (City city : cityList) {
//                    String content = "";
//                    content += "Name: " + city.getName() + "\n";
//                    content += "Code: " + city.getCode() + "\n";
//
//                    textViewResult.append(content);
//                }

                ArrayList<String> listCities = new ArrayList<>();
                for (int i=0;i<cityList.size();i++){
                    listCities.add(cityList.get(i).getName());
                    Log.i("City",cityList.get(i).getName());
                }

                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listCities);
                city.setAdapter(cityAdapter);
                city.setOnItemSelectedListener(QRCode.this);
            }


            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    public void genQR() {


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btGenQR.getId()) {
            genQR();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}