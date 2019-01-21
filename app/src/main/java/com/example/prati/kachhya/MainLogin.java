package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainLogin extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        Button stdLogin = (Button)findViewById(R.id.StudentLogin);
        stdLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainLogin.this,StudentLogin.class);
                startActivity(intent);
            }
        });

        Button teacherLogin = (Button) findViewById(R.id.TeacherLogin);
        teacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(MainLogin.this,TeacherLogin.class);
                startActivity(intent2);
            }
        });
        Button Login = (Button) findViewById(R.id.Signup_btn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(MainLogin.this,SignUpPage.class);
                startActivity(intent3);
            }
        });
    }

}
