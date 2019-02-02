package com.example.prati.kachhya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation animation;
    private static final String LOGIN_PREFERENCES = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        if (sharedpreferences.getBoolean("login", false)) {
            if (sharedpreferences.getString("type", "NULL").equals("Student")) {
                Intent intent = new Intent(MainActivity.this, Home_Student.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }


        animation = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setEnabled(isNetworkAvailable());
//        TODO: Once button is disabled it is disabled forever fix that...
        loginButton.setEnabled(isNetworkAvailable());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, SignUpPage.class);
                startActivity(intent1);
                finish();
            }
        });
        if (isNetworkAvailable() == false) {
            Toast.makeText(getApplicationContext(), "No Internet Connection!! Please Connect to any wifi Network or Turn on mobile data to proceed!!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}