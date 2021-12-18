package com.example.squeue.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.squeue.model.Address;
import com.example.squeue.queue.QueueManagement;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import com.example.squeue.R;
import com.example.squeue.home.Home;

public class GenQRCode extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome, qrCodeIV;
    private TextView tvAddress;
    private Button btSaveQr;
    private String city, district, ward, fullAddress;
    private Address address;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_qrcode);
        init();
        setOnClick();
        genQR();
    }

    public void init() {
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        tvAddress =findViewById(R.id.tvAddress);
        qrCodeIV = findViewById(R.id.ivIVQrcode);
        btSaveQr = findViewById(R.id.btSaveQr);

        Bundle bundle = getIntent().getExtras();
        city = bundle.getString("city");
        district = bundle.getString("district");
        ward = bundle.getString("ward");
        fullAddress = city + ", " + district + ", " + ward;
        tvAddress.setText(fullAddress);
        address = new Address(city,district,ward);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btSaveQr.setOnClickListener(this);
    }

    public void genQR() {
        // below line is for getting
        // the windowmanager service.
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(fullAddress, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            qrCodeIV.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }

    }

    public void saveQR() {
        Toast.makeText(this,"Create Successfully",Toast.LENGTH_LONG).show();
        //luu qr vao server ...

        Intent in = new Intent(this, QueueManagement.class);
        startActivity(in);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btSaveQr.getId()) {
            saveQR();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}