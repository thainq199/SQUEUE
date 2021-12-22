package com.example.squeue.queue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;
import com.example.squeue.model.Customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCustomerQueue extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome;
    private Button btNext, btBack, btRear;
    private TextView tvCustomerName, tvCustomerId;
    private List<Customer> listCustomer;
    private int id, todanpho_id;
    private String city, fullInfo, fullInfo2;
    private Queue<Customer> queueCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer_queue);
        init();
        setOnClick();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btNext = findViewById(R.id.btNext);
        //btBack = findViewById(R.id.btBack);
        btRear = findViewById(R.id.btRear);
        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvCustomerId = findViewById(R.id.tvCustomerId);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        city = bundle.getString("city");

        //Toast.makeText(this, "Id=" + id + ", city=" + city, Toast.LENGTH_LONG).show();

        listCustomer = new ArrayList<>();
        queueCustomer = new LinkedList<>();

        //getDataFromServer();

        queueCustomer.add(new Customer("001045006121", "Nguyễn Văn Nam", "20/3/1991", "0123456879",
                new Address("Hà Nội", "Đống Đa", "Trung Tự"), 1));
        queueCustomer.add(new Customer("001045006122", "Trần Quốc Cường", "10/11/1989", "012345435479",
                new Address("Hà Nội", "Đống Đa", "Trung Liệt"), 2));
        queueCustomer.add(new Customer("001045006123", "Đào Thị Thủy", "6/5/1979", "0123456879",
                new Address("Hà Nội", "Hoàn Kiếm", "Hàng Bài"), 0));
        queueCustomer.add(new Customer("001045006124", "Ngô Văn Sơn", "7/8/1995", "0123456879",
                new Address("Hà Nội", "Đống Đa", "Thái Hà"), 1));
        queueCustomer.add(new Customer("001045006125", "Nguyễn Thùy Linh", "30/9/1999", "0123456879",
                new Address("Hà Nội", "Đống Đa", "Phương Liệt"), 3));

        getFullInfo();

        if (!queueCustomer.isEmpty()) {
            tvCustomerName.setText(fullInfo);
            tvCustomerId.setText(fullInfo2);
        } else {
            Toast.makeText(this, "Hang doi rong", Toast.LENGTH_SHORT).show();
        }

    }

    public void getFullInfo() {
        fullInfo = queueCustomer.peek().getId().toString() + ", " + queueCustomer.peek().getName().toString()
                + ", " + queueCustomer.peek().getDob().toString();
        fullInfo2 =
                //queueCustomer.peek().getPhoneNum().toString() + ", "+
                queueCustomer.peek().getAddress().getCity().toString() + ", " + queueCustomer.peek().getAddress().getDistrict().toString()
                        + ", " + queueCustomer.peek().getAddress().getWard().toString();
        //+ ", "+queueCustomer.peek().getState().toString()
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btNext.setOnClickListener(this);
        // btBack.setOnClickListener(this);
        btRear.setOnClickListener(this);
    }

    public void nextBt() {
        if (!queueCustomer.isEmpty()) {
            Log.i("Dau q", queueCustomer.peek().getName().toString());
            Toast.makeText(this, "Đã phục vụ", Toast.LENGTH_SHORT).show();
            queueCustomer.poll();
            if (!queueCustomer.isEmpty()) {
                getFullInfo();
                tvCustomerName.setText(fullInfo);
                tvCustomerId.setText(fullInfo2);
            }
        } else {
            Toast.makeText(this, "Hàng đợi rỗng", Toast.LENGTH_SHORT).show();
        }
    }

    public void backBt() {

    }

    public void rearBt() {
        if (!queueCustomer.isEmpty()) {
            Customer c = queueCustomer.poll();
            Toast.makeText(this, "Đã đưa về cuối hàng đợi", Toast.LENGTH_SHORT).show();
            queueCustomer.add(c);
            getFullInfo();
            tvCustomerName.setText(fullInfo);
            tvCustomerId.setText(fullInfo2);
        } else {
            Toast.makeText(this, "Hàng đợi rỗng", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDataFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.130:3001/")
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

                listCustomer = response.body();
                for (int i = 0; i < listCustomer.size(); i++) {
                    queueCustomer.add(listCustomer.get(i));
                }

            }

            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btNext.getId()) {
            nextBt();
//        } else if (v.getId() == btBack.getId()) {
//            backBt();
        } else if (v.getId() == btRear.getId()) {
            rearBt();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}