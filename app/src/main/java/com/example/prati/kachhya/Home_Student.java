package com.example.prati.kachhya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Home_Student extends AppCompatActivity implements View.OnClickListener {

    private TextView email, username;
    private static final String LOGIN_PREFERENCES = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_student);

        email = findViewById(R.id.user_email);
        username = findViewById(R.id.user_name);

        SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String _email = sharedpreferences.getString("email", "example.com");
        String _username = sharedpreferences.getString("username", "Aashish");

        email.setText(_email);
        username.setText(_username);

        findViewById(R.id.assignment).setOnClickListener(Home_Student.this);
        findViewById(R.id.calender).setOnClickListener(Home_Student.this);
        findViewById(R.id.attendance).setOnClickListener(Home_Student.this);
        findViewById(R.id.logout).setOnClickListener(Home_Student.this);
        findViewById(R.id.qrcode).setOnClickListener(Home_Student.this);
        findViewById(R.id.routinebutton).setOnClickListener(Home_Student.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.assignment:
                startActivity(new Intent(Home_Student.this, Assignments.class));
                break;
            case R.id.calender:
                startActivity(new Intent(Home_Student.this, Calender.class));
                break;
            case R.id.attendance:
                startActivity(new Intent(Home_Student.this, Attendance.class));
                break;
            case R.id.qrcode:
                startActivity(new Intent(Home_Student.this, QR_CODE.class));
                break;
            case R.id.logout:
                SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Home_Student.this, MainActivity.class));
                finish();
                break;
            case R.id.routinebutton:
                startActivity(new Intent(Home_Student.this, Routine.class));
                break;
        }
    }
}
