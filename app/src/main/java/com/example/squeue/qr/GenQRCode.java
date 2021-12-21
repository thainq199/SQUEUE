package com.example.squeue.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.squeue.getAPI.JsonPlaceHolderApi;
import com.example.squeue.model.Address;
import com.example.squeue.model.Qr;
import com.example.squeue.queue.QueueManagement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.squeue.R;
import com.example.squeue.home.Home;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class GenQRCode extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack, ivHome, qrCodeIV;
    private TextView tvAddress;
    private Button btSaveQr;
    private String city, district, ward, fullAddress, vaccineName, date, time, jsonQr, todanpho, qr_string;
    private Address address;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference root;
    private int QrId = 1, todanpho_id, qr_id ;
    private long startTime, endTime;

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
        tvAddress = findViewById(R.id.tvAddress);
        qrCodeIV = findViewById(R.id.ivIVQrcode);
        btSaveQr = findViewById(R.id.btSaveQr);

        Bundle bundle = getIntent().getExtras();
        city = bundle.getString("city");
        district = bundle.getString("district");
        ward = bundle.getString("ward");
        todanpho = bundle.getString("todanpho");
        todanpho_id = bundle.getInt("todanpho_id");

        vaccineName = bundle.getString("vaccine");
        date = bundle.getString("date");
        time = bundle.getString("time");
        startTime = bundle.getLong("startTime");
        endTime = bundle.getLong("endTime");
        qr_id = getSharedPreference();

        fullAddress = city + ", " + district + ", " + ward + ", " + todanpho + ", " + vaccineName + ", " + date + ", " + time;
        tvAddress.setText(fullAddress);
        address = new Address(city, district, ward);

        qr_string = "" + todanpho_id + ", " + qr_id  + ", " + startTime + ", " +endTime;

        // get the Firebase storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        root = FirebaseDatabase.getInstance().getReference().child("Image");

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
        qrgEncoder = new QRGEncoder(qr_string, null, QRGContents.Type.TEXT, dimen);
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
        //Toast.makeText(this, "Create Successfully", Toast.LENGTH_LONG).show();
        //luu qr vao server ...

        // Code for showing progressDialog while uploading
        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        // Defining the child of storageReference
        StorageReference ref
                = storageReference
                .child(
                        "images/"
                                + UUID.randomUUID().toString());

        // adding listeners on upload
        // or failure of image
        ref.putFile(getImageUri(GenQRCode.this, bitmap))
                .addOnSuccessListener(
                        new OnSuccessListener<UploadTask.TaskSnapshot>() {

                            @Override
                            public void onSuccess(
                                    UploadTask.TaskSnapshot taskSnapshot) {

                                // Image uploaded successfully
                                // Dismiss dialog
                                progressDialog.dismiss();
                                Toast
                                        .makeText(GenQRCode.this,
                                                "Image Uploaded!!",
                                                Toast.LENGTH_LONG)
                                        .show();
                                // lay link anh
                                ref.getDownloadUrl().addOnSuccessListener(
                                        new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(@NonNull Uri uri) {
                                                String downloadUrl = uri.toString();
                                                //int qrId = root.push().getKey();
                                                //Qr qrCode = new Qr(root.push().getKey(),downloadUrl,"QR Code");

                                                int id = getSharedPreference();
                                                Qr qrCode = new Qr(id, downloadUrl, "QR Code");
                                                Log.d("downloadUrl", "" + downloadUrl);
                                                setSharedPreference(++id);

                                                //post obj len server...
                                                Gson gson = new Gson();
                                                jsonQr = gson.toJson(qrCode);
                                                //jsonQr=addQrJsonToServer(qrCode);
                                                postData(jsonQr);
                                                Log.d("jsonQr", "" + jsonQr);
                                            }
                                        });
                            }
                        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast
                                .makeText(GenQRCode.this,
                                        "Failed " + e.getMessage(),
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .addOnProgressListener(
                        new OnProgressListener<UploadTask.TaskSnapshot>() {

                            // Progress Listener for loading
                            // percentage on the dialog box
                            @Override
                            public void onProgress(
                                    UploadTask.TaskSnapshot taskSnapshot) {
                                double progress
                                        = (100.0
                                        * taskSnapshot.getBytesTransferred()
                                        / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage(
                                        "Uploaded "
                                                + (int) progress + "%");
                            }
                        });

        Intent in = new Intent(this, QueueManagement.class);
        startActivity(in);
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(GenQRCode.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(GenQRCode.this, new String[]{permission}, requestCode);
        } else {
            saveQR();
            //Toast.makeText(GenQRCode.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public void postData(String qr_code){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // calling a method to create a post and passing our modal class.
        Call<Qr> call = jsonPlaceHolderApi.createQr(qr_code);

        // on below line we are executing our method.
        call.enqueue(new Callback<Qr>() {
            @Override
            public void onResponse(Call<Qr> call, Response<Qr> response) {
                // this method is called when we get response from our api.
                Toast.makeText(GenQRCode.this, "Data added to API", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Qr> call, Throwable t) {

            }
        });
    }

    // This function is called when user accept or decline the permission.
// Request Code is used to check which permission called this function.
// This request code is provided when user is prompt for permission.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveQR();

                Toast.makeText(GenQRCode.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GenQRCode.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String addQrJsonToServer(Qr qrcode) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", qrcode.getId());
            jsonObject.put("duongdan", qrcode.getDuongdan());
            jsonObject.put("mota", qrcode.getMota());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public int getQrId(){
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        QrId = sh.getInt("QrId",QrId);
        return QrId;
    }

    public void setSharedPreference(int id){
        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putInt("QrId", id);
        myEdit.apply();
    }
    public int getSharedPreference(){
        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        QrId = sh.getInt("QrId",1);
        return QrId;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btSaveQr.getId()) {
            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == ivHome.getId()) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        }
    }
}