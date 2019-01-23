package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignUpPage extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        findViewById(R.id.stdSignUP).setOnClickListener(SignUpPage.this);
        findViewById(R.id.TeacherSignUp).setOnClickListener(SignUpPage.this);
        findViewById(R.id.Login_btn).setOnClickListener(SignUpPage.this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           case R.id.stdSignUP:
                startActivity(new Intent(SignUpPage.this, StudentSignUP.class));
                 break;
            case R.id.TeacherSignUp:
                startActivity(new Intent(SignUpPage.this,TeacherSignUp.class));
                break;
            case R.id.Login_btn:
                startActivity(new Intent(SignUpPage.this, MainLogin.class));

        }
    }
}
