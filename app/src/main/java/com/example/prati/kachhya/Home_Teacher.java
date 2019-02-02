package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Home_Teacher extends AppCompatActivity implements View.OnClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_teacher);
        findViewById(R.id.seeattendance).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.scanqr).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.inputassignment).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.inputroutine).setOnClickListener(Home_Teacher.this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.seeattendance:
                startActivity(new Intent(Home_Teacher.this, Attendance_Detail.class));
                break;
            case R.id.inputassignment:
                startActivity(new Intent(Home_Teacher.this, Assignment_add.class));
                break;
            case R.id.inputroutine:
                startActivity(new Intent(Home_Teacher.this, Routine_Add.class));
                break;
            case R.id.scanqr:
                break;
        }
    }
}
