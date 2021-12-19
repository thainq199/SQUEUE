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
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;
import com.example.squeue.model.Customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ListCustomerQueue extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome;
    private Button btNext, btBack, btRear;
    private TextView tvCustomerName, tvCustomerId;
    private Customer customer;
    private ArrayList<Customer> listCustomer;
    private int id;
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

        Toast.makeText(this, "Id=" + id + ", city=" + city, Toast.LENGTH_LONG).show();

        listCustomer = new ArrayList<>();
        queueCustomer = new LinkedList<>();

        queueCustomer.add(new Customer("001045006121", "Nguyen Van Hoai Nam", "20/3/1999", "0123456879",
                new Address("Ha Noi", "Dong Da", "Trung Tu"), 1));
        queueCustomer.add(new Customer("001045006122", "Nam 2", "10/11/1999", "012345435479",
                new Address("Ha Noi", "Dong Da", "Trung Liet"), 2));
        queueCustomer.add(new Customer("001045006123", "Nam 3", "6/5/1999", "0123456879",
                new Address("Ha Noi", "Hoan Kiem", "Hang Bai"), 0));
        queueCustomer.add(new Customer("001045006124", "Nam 4 ", "7/8/1999", "0123456879",
                new Address("Ha Noi", "Dong Da", "Thai Ha"), 1));
        queueCustomer.add(new Customer("001045006125", "Nam 5", "30/9/1999", "0123456879",
                new Address("Ha Noi", "Dong Da", "Phuong Liet"), 3));

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
            Toast.makeText(this, "Da phuc vu", Toast.LENGTH_SHORT).show();
            queueCustomer.poll();
            if (!queueCustomer.isEmpty()) {
                getFullInfo();
                tvCustomerName.setText(fullInfo);
                tvCustomerId.setText(fullInfo2);
            }
        } else {
            Toast.makeText(this, "Hang doi rong", Toast.LENGTH_SHORT).show();
        }
    }

    public void backBt() {

    }

    public void rearBt() {
        if (!queueCustomer.isEmpty()) {
            Customer c = queueCustomer.poll();
            Toast.makeText(this, "Da dua ve cuoi Queue", Toast.LENGTH_SHORT).show();
            queueCustomer.add(c);
            getFullInfo();
            tvCustomerName.setText(fullInfo);
            tvCustomerId.setText(fullInfo2);
        } else {
            Toast.makeText(this, "Hang doi rong", Toast.LENGTH_SHORT).show();
        }
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