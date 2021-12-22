package com.example.squeue.qr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
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
import com.example.squeue.model.Phuong;
import com.example.squeue.model.Quan;
import com.example.squeue.model.Todanpho;
import com.example.squeue.model.Vaccine;
import com.example.squeue.model.Ward;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QRCode extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private ImageView ivBack, ivHome;
    private Button btGenQR, btDate, btTime, btEndDate, btEndTime;
    private TextView tvDate, tvTime, tvEndDate, tvEndTime;
    private Spinner city, district, ward, vaccine, todanpho;
    private List<City> cityList;
    private List<Quan> districtsList;
    private List<Phuong> wardsList;
    private List<Todanpho> todanphoList;
    private List<Vaccine> vaccineList;
    private String spinnerCity, spinnerDistrict, spinnerWard, spinnerVaccine, spinnertoDanPho, dateText, timeText;
    private int province_code = 1, district_code = 1, todanpho_id, qr_id;
    private long startTime, endTime, timeInMilliseconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        init();
        setOnClick();
        //getAPI();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btGenQR = findViewById(R.id.btGenQr);
        city = findViewById(R.id.spinnerCity);
        district = findViewById(R.id.spinnerDistrict);
        ward = findViewById(R.id.spinnerWard);
        todanpho = findViewById(R.id.spinnerTodanpho);
        vaccine = findViewById(R.id.spinnerVaccine);
        btDate = findViewById(R.id.btDate);
        btTime = findViewById(R.id.btTime);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        btEndDate = findViewById(R.id.btEndDate);
        btEndTime = findViewById(R.id.btEndTime);
        tvEndDate = findViewById(R.id.tvEndDate);
        tvEndTime = findViewById(R.id.tvEndTime);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btGenQR.setOnClickListener(this);
        btDate.setOnClickListener(this);
        btTime.setOnClickListener(this);
        btEndDate.setOnClickListener(this);
        btEndTime.setOnClickListener(this);
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
//                ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listCities);
//                city.setAdapter(cityAdapter);
//                city.setOnItemSelectedListener(QRCode.this);
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
//                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
//                district.setAdapter(districtAdapter);
//                district.setOnItemSelectedListener(QRCode.this);
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
//                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listWards);
//                ward.setAdapter(wardAdapter);
//                ward.setOnItemSelectedListener(QRCode.this);
//            }
//
//
//            @Override
//            public void onFailure(Call<List<Ward>> call, Throwable t) {
//
//            }
//        });
//
//        //vaccine
//        vaccineList = new ArrayList<>();
//        vaccineList.add("Moderna");
//        vaccineList.add("Sinovac");
//        vaccineList.add("AstraZeneca");
//        vaccineList.add("Pfizer");
//        vaccineList.add("Sinopharm");
//        vaccineList.add("Sputnik");
//
//        ArrayAdapter<String> vaccineAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, vaccineList);
//        vaccine.setAdapter(vaccineAdapter);
//        vaccine.setOnItemSelectedListener(QRCode.this);
//
//        //date time
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
        Call<List<Vaccine>> VaccineCall = jsonPlaceHolderApi.getVaccine();

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

                ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listDistrict);
                district.setAdapter(districtAdapter);
                district.setOnItemSelectedListener(QRCode.this);
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

                ArrayAdapter<String> wardAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listWards);
                ward.setAdapter(wardAdapter);
                ward.setOnItemSelectedListener(QRCode.this);
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

                ArrayAdapter<String> todanphoAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listTodanpho);
                todanpho.setAdapter(todanphoAdapter);
                todanpho.setOnItemSelectedListener(QRCode.this);
            }


            @Override
            public void onFailure(Call<List<Todanpho>> call, Throwable t) {

            }
        });

        VaccineCall.enqueue(new Callback<List<Vaccine>>() {
            @Override
            public void onResponse(Call<List<Vaccine>> call, Response<List<Vaccine>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                vaccineList = response.body();

                ArrayList<String> listVaccine = new ArrayList<>();
                for (int i = 0; i < vaccineList.size(); i++) {
                    //if(wardsList.get(i).getDistrict_code()==district_code) {
                    listVaccine.add(vaccineList.get(i).getTen());
                    //}
                }

                ArrayAdapter<String> vaccineAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listVaccine);
                vaccine.setAdapter(vaccineAdapter);
                vaccine.setOnItemSelectedListener(QRCode.this);
            }


            @Override
            public void onFailure(Call<List<Vaccine>> call, Throwable t) {

            }
        });
        ArrayList<String> listCity = new ArrayList<>();
        listCity.add("Thành phố Hà Nội");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, listCity);
        city.setAdapter(cityAdapter);
        city.setOnItemSelectedListener(QRCode.this);

        //vaccine
//        vaccineList = new ArrayList<>();
//        vaccineList.add("Moderna");
//        vaccineList.add("Sinovac");
//        vaccineList.add("AstraZeneca");
//        vaccineList.add("Pfizer");
//        vaccineList.add("Sinopharm");
//        vaccineList.add("Sputnik");

//        ArrayAdapter<String> vaccineAdapter = new ArrayAdapter<>(QRCode.this, android.R.layout.simple_spinner_dropdown_item, vaccineList);
//        vaccine.setAdapter(vaccineAdapter);
//        vaccine.setOnItemSelectedListener(QRCode.this);

        //date time
    }

    public void genQR() {

        //truyen address sang GenQR
        Intent in = new Intent(this, GenQRCode.class);
        in.putExtra("city", spinnerCity);
        in.putExtra("district", spinnerDistrict);
        in.putExtra("ward", spinnerWard);
        in.putExtra("todanpho", spinnertoDanPho);
        in.putExtra("todanpho_id", todanpho_id);
        in.putExtra("vaccine", spinnerVaccine);
        in.putExtra("date", dateText);
        in.putExtra("time", timeText);
        in.putExtra("startTime", startTime);
        in.putExtra("endTime", endTime);
        startActivity(in);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        spinnerCity = city.getSelectedItem().toString();
        spinnerDistrict = district.getSelectedItem().toString();
        spinnerWard = ward.getSelectedItem().toString();
        spinnerVaccine = vaccine.getSelectedItem().toString();
        spinnertoDanPho = todanpho.getSelectedItem().toString();
        for (int i = 0; i < todanphoList.size(); i++) {
            if (todanphoList.get(i).getTen().equals(spinnertoDanPho)) {
                todanpho_id = todanphoList.get(i).getId();
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void handleDateButton(int d) {
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
                dateText = DateFormat.format("yyyy-MM-dd", calendar1).toString();
                if (d == 1)
                    tvDate.setText(dateText);
                else tvEndDate.setText(dateText);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    private void handleTimeButton(int d) {
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
                timeText = DateFormat.format("HH:mm:ss", calendar1).toString();
                if (d == 1)
                    tvTime.setText(timeText);
                else tvEndTime.setText(timeText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }

    public long convertDate() {
        String givenDateString = dateText+" "+timeText;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date mDate = sdf.parse(givenDateString);
            timeInMilliseconds = mDate.getTime()/1000;
            Log.d("Date in milli :: " ,""+timeInMilliseconds);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilliseconds;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        if (v.getId() == btGenQR.getId()) {
            endTime = convertDate();
            Log.d("End time: " ,""+endTime);
            genQR();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        } else if (v.getId() == btDate.getId()) {
            handleDateButton(1);
        } else if (v.getId() == btTime.getId()) {
            handleTimeButton(1);

        } else if (v.getId() == btEndDate.getId()) {
            handleDateButton(2);
            startTime = convertDate();
            Log.d("Start time: " ,""+startTime);
        } else if (v.getId() == btEndTime.getId()) {
            handleTimeButton(2);
        }
    }
}