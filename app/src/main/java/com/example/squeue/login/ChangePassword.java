package com.example.squeue.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.squeue.R;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener{
    private EditText etnewPassword, etconfirmNewPassword;
    private Button btChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();
        setOnClick();

    }

    public void init() {
        //popup
        etnewPassword = findViewById(R.id.etnewPassword);
        etconfirmNewPassword = findViewById(R.id.etconfirmNewPassword);
        btChangePassword = findViewById(R.id.btChangePassword);

    }

    public void setOnClick() {
        btChangePassword.setOnClickListener(this);
    }

    public void changePassword() {
        if(etnewPassword.length() == 0 || etconfirmNewPassword.length() == 0){
            Toast.makeText(this,"Mật khẩu không được trống",Toast.LENGTH_SHORT).show();
        }
        else if(!(etnewPassword.getText().toString().equals(etconfirmNewPassword.getText().toString()))){
            Toast.makeText(this,"Mật khẩu không khớp nhau",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Đổi mật khẩu thành công",Toast.LENGTH_LONG).show();
            Intent in = new Intent(this,Login.class);
            startActivity(in);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btChangePassword.getId()) {
            changePassword();
        }
    }
}