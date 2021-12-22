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

public class QueueManagement extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome;
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
        listQueue = new ArrayList<>();

        listQueue.add(new Address(1, "Hà Nội, Đống Đa, Trung Tự", "Đống Đa", "Trung Tự"));
        listQueue.add(new Address(2, "Hà Nội, Đống Đa, Cát Linh", "Đống Đa", "Cát Linh"));
        listQueue.add(new Address(3, "Hà Nội, Đống Đa, Ô Chợ Dừa", "Đống Đa", "Ô Chợ Dừa"));
        listQueue.add(new Address(4, "Hà Nội, Ba Đình, Trúc Bạch", "Ba Đình", "Trúc Bạch"));
        listQueue.add(new Address(5, "Hà Nội, Ba Đình, Liễu Giai", "Ba Đình", "Liễu Giai"));
        listQueue.add(new Address(6, "Hà Nội, Hoàn Kiếm, Hàng Buồm", "Hoàn Kiếm", "Hàng Buồm"));
        listQueue.add(new Address(7, "Hà Nội, Hoàn Kiếm, Hàng Bông", "Hoàn Kiếm", "Hàng Bông"));
        listQueue.add(new Address(8, "Hà Nội, Hoàn Kiếm, Hàng Bài", "Hoàn Kiếm", "Hàng Bài"));
        listQueue.add(new Address(9, "Hà Nội, Hoàng Mai, Đại Kim", "Hoàng Mai", "Đại Kim"));


        queueListViewAdapter = new QueueListViewAdapter(listQueue);

        listview_queue = findViewById(R.id.listview_queue);
        listview_queue.setAdapter(queueListViewAdapter);

        //Lắng nghe bắt sự kiện một phần tử danh sách được chọn
        listview_queue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Address address = (Address) queueListViewAdapter.getItem(position);
                //Làm gì đó khi chọn sản phẩm (ví dụ tạo một Activity hiện thị chi tiết, biên tập ..)

                Intent in = new Intent(QueueManagement.this, ListCustomerQueue.class);
                in.putExtra("id", address.getId());
                in.putExtra("city", address.getCity());
                startActivity(in);

                //Toast.makeText(QueueManagement.this, address.getCity(), Toast.LENGTH_LONG).show();
            }
        });

//        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listQueue.size() > 0) {
//                    //Xoá phần tử đầu tiên của danh sách
//                    int productpost = 0;
//                    listQueue.remove(productpost);
//                    //Thông báo cho ListView biết dữ liệu đã thay đổi (cập nhật, xoá ...)
//                    queueListViewAdapter.notifyDataSetChanged();
//                }
//            }
//        });

    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
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