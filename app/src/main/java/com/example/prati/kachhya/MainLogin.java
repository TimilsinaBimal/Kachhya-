package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainLogin extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        findViewById(R.id.StudentLogin).setOnClickListener(MainLogin.this);
        findViewById(R.id.TeacherLogin).setOnClickListener(MainLogin.this);
        findViewById(R.id.Signup_btn).setOnClickListener(MainLogin.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.StudentLogin:
                startActivity(new Intent(MainLogin.this, StudentLogin.class));
                break;
            case R.id.TeacherLogin:
                startActivity(new Intent(MainLogin.this, TeacherLogin.class));
                break;
            case R.id.Signup_btn:
                startActivity(new Intent(MainLogin.this, SignUpPage.class));
        }
    }
}
