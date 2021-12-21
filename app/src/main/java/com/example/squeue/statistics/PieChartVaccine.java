package com.example.squeue.statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import com.example.squeue.R;
import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;
import com.example.squeue.model.Customer;
import com.example.squeue.model.Phuong;
import com.example.squeue.model.Qr;
import com.example.squeue.model.Quan;
import com.example.squeue.model.Todanpho;
import com.example.squeue.qr.GenQRCode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PieChartVaccine extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome;
    private TextView tvDose0, tvDose1, tvDose2, tvDose3;
    private PieChart pieChart;
    private String city, district, ward, fullAddress, todanpho;
    private Address address;
    private Customer customer;
    private int dose0 = 0, dose1 = 0, dose2 = 0, dose3 = 0, todanpho_id;
    private List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        init();
        setOnClick();
        getDataFromServer();
        setData();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        tvDose0 = findViewById(R.id.tvDose0);
        tvDose1 = findViewById(R.id.tvDose1);
        tvDose2 = findViewById(R.id.tvDose2);
        tvDose3 = findViewById(R.id.tvDose3);
        pieChart = findViewById(R.id.piechart);
        customerList = new ArrayList<>();
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
    }

//    public void getDataFromServer() {
//        Bundle bundle = getIntent().getExtras();
//        city = bundle.getString("city");
//        district = bundle.getString("district");
//        ward = bundle.getString("ward");
//        //lay 1 list customer
//
//        //dem customer.state
//        customerList = new ArrayList<>();
//        customerList.add(new Customer("0", "2", "3", "4", null, 0));
//        customerList.add(new Customer("1", "2", "3", "4", null, 1));
//        customerList.add(new Customer("2", "2", "3", "4", null, 2));
//        customerList.add(new Customer("3", "2", "3", "4", null, 3));
//        customerList.add(new Customer("4", "2", "3", "4", null, 0));
//        customerList.add(new Customer("5", "2", "3", "4", null, 1));
//        customerList.add(new Customer("6", "2", "3", "4", null, 2));
//        customerList.add(new Customer("7", "2", "3", "4", null, 2));
//        customerList.add(new Customer("8", "2", "3", "4", null, 2));
//        customerList.add(new Customer("9", "2", "3", "4", null, 2));
//
//        for (int i = 0; i < customerList.size(); i++) {
////            if (customerList.get(i).getAddress().getCity().equals(city) &&
////                    customerList.get(i).getAddress().getDistrict().equals(district) &&
////                    customerList.get(i).getAddress().getWard().equals(ward))
////            {
//            if (customerList.get(i).getState() == 0) dose0++;
//            else if (customerList.get(i).getState() == 1) dose1++;
//            else if (customerList.get(i).getState() == 2) dose2++;
//            else if (customerList.get(i).getState() == 3) dose3++;
//            // }
//        }
//    }

    public void setData() {

        // Set the percentage
        tvDose0.setText(String.valueOf(dose0));
        tvDose1.setText(String.valueOf(dose1));
        tvDose2.setText(String.valueOf(dose2));
        tvDose3.setText(String.valueOf(dose3));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "dose0",
                        Integer.parseInt(tvDose0.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "dose1",
                        Integer.parseInt(tvDose1.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "dose2",
                        Integer.parseInt(tvDose2.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "dose3",
                        Integer.parseInt(tvDose3.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    public void getDataFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://provinces.open-api.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Customer>> customer = jsonPlaceHolderApi.getcustomer(todanpho_id);

        customer.enqueue(new Callback<List<Customer>>() {
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                customerList = response.body();
                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).getState() == 0) dose0++;
                    else if (customerList.get(i).getState() == 1) dose1++;
                    else if (customerList.get(i).getState() == 2) dose2++;
                    else if (customerList.get(i).getState() == 3) dose3++;
                }
            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}