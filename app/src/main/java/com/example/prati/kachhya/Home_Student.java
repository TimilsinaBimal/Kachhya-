package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Home_Student extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_student);
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
                break;
            case R.id.logout:
                break;
            case R.id.routinebutton:
                startActivity(new Intent(Home_Student.this, Routine.class));
                break;
        }
    }
}
