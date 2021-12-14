package com.example.squeue.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.model.HomeMenu;

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
                Object o = gridView.getItemAtPosition(position);
                HomeMenu homeMenu = (HomeMenu) o;
                Toast.makeText(Home.this, "Selected :"
                        + " " + homeMenu, Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<HomeMenu> getListData() {
        List<HomeMenu> list = new ArrayList<HomeMenu>();
        HomeMenu queue = new HomeMenu("Quản lý hàng chờ", "queue");
        HomeMenu qr = new HomeMenu("Sinh QR Code", "qr");
        HomeMenu statistics = new HomeMenu("Xem thống kê", "statistics");
        HomeMenu user = new HomeMenu("      Cá nhân", "user");

        list.add(queue);
        list.add(qr);
        list.add(statistics);
        list.add(user);

        return list;
    }

}