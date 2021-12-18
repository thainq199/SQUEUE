package com.example.squeue.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.squeue.R;
import com.example.squeue.login.ChangePassword;
import com.example.squeue.login.Login;
import com.example.squeue.model.Account;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class UserSetting extends AppCompatActivity implements View.OnClickListener{
    private TextView tvEditUser;
    private EditText etUser_name, etUser_email, et_User_role, etUser_phoneNum;
    private int EditUserStatus = 0;
    private LinearLayout layoutChangePassword;
    private Button btLogout;
    private ImageView ivBack,ivHome;
    private Account account;
    private GoogleSignInClient mGoogleSignInClient;
    private String personName,personGivenName,personFamilyName,personEmail,personId;
    private Uri personPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            personName = acct.getDisplayName();
            personGivenName = acct.getGivenName();
            personFamilyName = acct.getFamilyName();
            personEmail = acct.getEmail();
            personId = acct.getId();
            personPhoto = acct.getPhotoUrl();

            //Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }

        init();
        setOnClick();
        //loadUserToForm();
    }


    public void init() {
        tvEditUser = findViewById(R.id.tvEditUser);
        etUser_name = findViewById(R.id.etUser_name);
        etUser_email = findViewById(R.id.etUser_email);
        et_User_role = findViewById(R.id.et_User_role);
        etUser_phoneNum = findViewById(R.id.etUser_phoneNum);
        layoutChangePassword = findViewById(R.id.layoutChangePassword);
        ivBack = findViewById(R.id.ivBack);
        ivHome = findViewById(R.id.ivHome);
        btLogout = findViewById(R.id.btLogout);

        etUser_name.setText(personName);
        etUser_email.setText(personEmail);
    }

    public void setOnClick() {
        tvEditUser.setOnClickListener(this);
        layoutChangePassword.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        btLogout.setOnClickListener(this);
    }

    public void AllowEdit(int check) {

        if (check == 0) {
            etUser_name.setEnabled(true);
            etUser_email.setEnabled(true);
            et_User_role.setEnabled(true);
            etUser_phoneNum.setEnabled(true);
            tvEditUser.setText("Lưu");
            EditUserStatus = 1;
        } else {
            etUser_name.setEnabled(false);
            etUser_email.setEnabled(false);
            et_User_role.setEnabled(false);
            etUser_phoneNum.setEnabled(false);
            tvEditUser.setText("Sửa");
            EditUserStatus = 0;
        }

        // luu vao csdl ...

    }

    public void loadUserToForm() {
        //lay dl tu csdl
        //set text...
        account = new Account("1","123","Nguyen Quoc Thai","thainq@gmail.com","Admin","0123");
        etUser_name.setText(account.getName());
        etUser_email.setText(account.getEmail());
        et_User_role.setText(account.getRole());
        etUser_phoneNum.setText(account.getPhone());
    }

    public void changePassword() {
        Intent in = new Intent(this, ChangePassword.class);
        in.putExtra("code", 1);
        startActivity(in);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tvEditUser.getId()) {
            AllowEdit(EditUserStatus);
        } else if (v.getId() == layoutChangePassword.getId()) {
            changePassword();
        } else if (v.getId() == ivBack.getId()) {
            finish();
        }
        else if (v.getId() == ivHome.getId()) {
            finish();
        }
        else if (v.getId() == btLogout.getId()) {
            signOut();
            revokeAccess();
            Intent in = new Intent(this, Login.class);
            startActivity(in);
        }

    }
}