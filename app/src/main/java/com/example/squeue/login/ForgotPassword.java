package com.example.squeue.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.squeue.R;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivBack;
    private EditText etusername, etemail;
    private Button btForgetPass, btOtp;
    private Dialog myDiaglog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
        setOnClick();
    }

    public void init() {
        //popup
        myDiaglog = new Dialog(this);
        myDiaglog.setCanceledOnTouchOutside(true);
        myDiaglog.setContentView(R.layout.forgotpass_popup);
        btOtp = myDiaglog.findViewById(R.id.btOtp);

        ivBack = findViewById(R.id.ivBack);
        etusername = findViewById(R.id.etusername);
        etemail = findViewById(R.id.etemail);
        btForgetPass = findViewById(R.id.btForgetPass);
    }

    public void setOnClick() {
        ivBack.setOnClickListener(this);
        btForgetPass.setOnClickListener(this);
        btOtp.setOnClickListener(this);
    }

    public void forgotPassword() {
        //gui otp ve email
    }

    public void OtpConfirm() {
        //if(opt is right)
        Intent in = new Intent(this, Login.class);
        startActivity(in);
    }

    public void onClickOtp() {
        if (etusername.length() == 0 || etemail.length() == 0) {
            Toast.makeText(this, "Tên đăng nhập hoặc email không được trống", Toast.LENGTH_LONG).show();
        } else {
            //if(ton tai username )...
            myDiaglog.show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btForgetPass.getId()) {
            onClickOtp();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        } else if (v.getId() == btOtp.getId()) {
            OtpConfirm();
        }
    }
}