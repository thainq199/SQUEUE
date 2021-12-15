package com.example.squeue.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.home.Home;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText etusername, etpassword;
    private TextView tvforgotPassword;
    private Button btLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setOnClick();
    }

    public void init() {
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        tvforgotPassword = findViewById(R.id.tvforgotPassword);
        btLogin = findViewById(R.id.btLogin);
    }

    public void setOnClick() {
        tvforgotPassword.setOnClickListener(this);
        btLogin.setOnClickListener(this);
    }


    public void forgotPassword() {
        Intent in = new Intent(this, ForgotPassword.class);
        startActivity(in);
    }

    public void login() {
        if (etusername.length() == 0 || etpassword.length() == 0) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được trống", Toast.LENGTH_LONG).show();
        } else {
            //check neu dung
            //if...
            Intent in = new Intent(this, Home.class);
            startActivity(in);

            //neu sai
            //else
            //Toast.makeText(this,"Tên đăng nhập hoặc mật khẩu không đúng",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == tvforgotPassword.getId()) {
            forgotPassword();
        } else if (v.getId() == btLogin.getId()) {
            login();
        }
    }
}