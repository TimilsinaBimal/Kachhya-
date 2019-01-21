package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpPage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        Button stdSignUp = (Button)findViewById(R.id.stdSignUP);
        stdSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignUpPage.this,StudentSignUP.class);
                startActivity(intent);
            }
        });

        Button teacherSignUp = (Button) findViewById(R.id.TeacherSignUp);
        teacherSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(SignUpPage.this,TeacherSignUp.class);
                startActivity(intent2);
            }
        });
        Button Login = (Button) findViewById(R.id.Login_btn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.setClass(SignUpPage.this,MainLogin.class);
                startActivity(intent3);
            }
        });
    }

}
