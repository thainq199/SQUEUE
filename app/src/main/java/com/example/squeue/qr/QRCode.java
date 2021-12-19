package com.example.squeue.qr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;
import com.example.squeue.model.City;
import com.example.squeue.model.District;
import com.example.squeue.model.Ward;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Button btGenQR, btDate, btTime;
    private TextView tvDate, tvTime;
    private Spinner city, district, ward, vaccine;
    private List<City> cityList;
    private List<District> districtsList;
    private List<Ward> wardsList;
    private List<String> vaccineList;
    private String spinnerCity, spinnerDistrict, spinnerWard, spinnerVaccine, dateText, timeText;
    private int province_code = 1, district_code = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        init();
        setOnClick();
        getAPI();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btGenQR = findViewById(R.id.btGenQr);
        city = findViewById(R.id.spinnerCity);
        district = findViewById(R.id.spinnerDistrict);
        ward = findViewById(R.id.spinnerWard);
        vaccine = findViewById(R.id.spinnerVaccine);
        btDate = findViewById(R.id.btDate);
        btTime = findViewById(R.id.btTime);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btGenQR.setOnClickListener(this);
        btDate.setOnClickListener(this);
        btTime.setOnClickListener(this);
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

                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listCities);
                city.setAdapter(cityAdapter);
                city.setOnItemSelectedListener(QRCode.this);
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

                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
                district.setAdapter(districtAdapter);
                district.setOnItemSelectedListener(QRCode.this);
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

                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listWards);
                ward.setAdapter(wardAdapter);
                ward.setOnItemSelectedListener(QRCode.this);
            }


            @Override
            public void onFailure(Call<List<Ward>> call, Throwable t) {

            }
        });

        //vaccine
        vaccineList = new ArrayList<>();
        vaccineList.add("Moderna");
        vaccineList.add("Sinovac");
        vaccineList.add("AstraZeneca");
        vaccineList.add("Pfizer");
        vaccineList.add("Sinopharm");
        vaccineList.add("Sputnik");

        ArrayAdapter<String> vaccineAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, vaccineList);
        vaccine.setAdapter(vaccineAdapter);
        vaccine.setOnItemSelectedListener(QRCode.this);

        //date time
    }

    public void genQR() {
        //truyen address sang GenQR
        Intent in = new Intent(this, GenQRCode.class);
        in.putExtra("city", spinnerCity);
        in.putExtra("district", spinnerDistrict);
        in.putExtra("ward", spinnerWard);
        in.putExtra("vaccine", spinnerVaccine);
        in.putExtra("date", dateText);
        in.putExtra("time", timeText);
        startActivity(in);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        spinnerCity = city.getSelectedItem().toString();
        spinnerDistrict = district.getSelectedItem().toString();
        spinnerWard = ward.getSelectedItem().toString();
        spinnerVaccine = vaccine.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                tvDate.setText(dateText);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i("Time", "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                timeText = DateFormat.format("h:mm a", calendar1).toString();

                tvTime.setText(timeText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
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
        } else if (v.getId() == btDate.getId()) {
            handleDateButton();
        } else if (v.getId() == btTime.getId()) {
            handleTimeButton();
        }
    }
}