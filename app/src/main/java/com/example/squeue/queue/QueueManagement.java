package com.example.squeue.queue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.squeue.MainActivity;
import com.example.squeue.R;
import com.example.squeue.home.Home;
import com.example.squeue.model.Address;

import java.util.ArrayList;

public class QueueManagement extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivBack, ivHome;
    private Button btDelete, btStart;
    private ArrayList<Address> listQueue;
    QueueListViewAdapter queueListViewAdapter;
    ListView listview_queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_management);

        init();
        setOnClick();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
//        btDelete = findViewById(R.id.btDelete);
//        btStart = findViewById(R.id.btStart);
        listQueue = new ArrayList<>();

        listQueue.add(new Address(1,"HN","Dong Da","Trung Tu"));
        listQueue.add(new Address(2,"HN","Dong Da","Trung Liet"));
        listQueue.add(new Address(3,"HN","Ba Dinh","Phuc Xa "));
        listQueue.add(new Address(4,"HN","Ba Dinh","Truc Bach"));
        listQueue.add(new Address(5,"HN","Ba Dinh","Lieu Giai"));
        listQueue.add(new Address(6,"HN","Hoan Kiem","Hang Buom"));
        listQueue.add(new Address(7,"HN","Hoan Kiem","Hang Bong"));
        listQueue.add(new Address(8,"HN","Hoan Kiem","Hang Bai"));
        listQueue.add(new Address(9,"HN","Dong Da","O Cho Dua"));


        queueListViewAdapter = new QueueListViewAdapter(listQueue);

        listview_queue = findViewById(R.id.listview_queue);
        listview_queue.setAdapter(queueListViewAdapter);

        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn
        listview_queue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = (Address) queueListViewAdapter.getItem(position);
                //Làm gì đó khi chọn sản phẩm (ví dụ tạo một Activity hiện thị chi tiết, biên tập ..)
                Toast.makeText(QueueManagement.this, address.getCity(), Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listQueue.size() > 0) {
                    //Xoá phần tử đầu tiên của danh sách
                    int productpost = 0;
                    listQueue.remove(productpost);
                    //Thông báo cho ListView biết dữ liệu đã thay đổi (cập nhật, xoá ...)
                    queueListViewAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
//        btDelete.setOnClickListener(this);
//        btStart.setOnClickListener(this);
    }

    public void startQueue() {

    }


    public void deleteQueue() {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btStart.getId()) {
            startQueue();
        }
        else if (v.getId() == btDelete.getId()) {
            deleteQueue();
        }else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}