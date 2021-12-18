package com.example.squeue.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.squeue.R;
import com.example.squeue.home.Home;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText etusername, etpassword;
    private TextView tvforgotPassword;
    private Button btLogin;
    private SignInButton google_sign_in_button;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setOnClick();
        googleLogin();
    }

    public void init() {
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        tvforgotPassword = findViewById(R.id.tvforgotPassword);
        btLogin = findViewById(R.id.btLogin);
        google_sign_in_button = findViewById(R.id.google_sign_in_button);
    }

    public void setOnClick() {
        tvforgotPassword.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        google_sign_in_button.setOnClickListener(this);
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

    public void googleLogin() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent in = new Intent(this, Home.class);
            startActivity(in);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //gui acc sang user...
        //updateUI(account);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tvforgotPassword.getId()) {
            forgotPassword();
        } else if (v.getId() == btLogin.getId()) {
            login();
        } else if (v.getId() == google_sign_in_button.getId()) {
            Toast.makeText(this,"Google",Toast.LENGTH_SHORT).show();
            googleLogin();
        }
    }
}