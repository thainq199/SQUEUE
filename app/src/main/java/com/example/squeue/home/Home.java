package com.example.squeue.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.model.HomeMenu;
import com.example.squeue.qr.QRCode;
import com.example.squeue.queue.QueueLine;
import com.example.squeue.statistics.StatisticsVaccine;
import com.example.squeue.user.UserSetting;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<HomeMenu> image_details = getListData();
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new CustomGridAdapter(this, image_details));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Object o = gridView.getItemAtPosition(position);
//                HomeMenu homeMenu = (HomeMenu) o;
//                Toast.makeText(Home.this, "Selected :"
//                        + " " + homeMenu, Toast.LENGTH_LONG).show();
                if (position == 0) {
                    Intent in = new Intent(Home.this, QueueLine.class);
                    startActivity(in);

                } else if (position == 1) {
                    Intent in = new Intent(Home.this, QRCode.class);
                    startActivity(in);

                } else if (position == 2) {
                    Intent in = new Intent(Home.this, StatisticsVaccine.class);
                    startActivity(in);

                } else if (position == 3) {
                    Intent in = new Intent(Home.this, UserSetting.class);
                    startActivity(in);
                }
            }
        });
    }

    private List<HomeMenu> getListData() {
        List<HomeMenu> list = new ArrayList<HomeMenu>();
        HomeMenu queue = new HomeMenu("queue", "Quản lý hàng chờ");
        HomeMenu qr = new HomeMenu("qr", "Sinh QR Code");
        HomeMenu statistics = new HomeMenu("statistics", "Xem thống kê");
        HomeMenu user = new HomeMenu("user", "      Cá nhân");

        list.add(queue);
        list.add(qr);
        list.add(statistics);
        list.add(user);

        return list;
    }

}