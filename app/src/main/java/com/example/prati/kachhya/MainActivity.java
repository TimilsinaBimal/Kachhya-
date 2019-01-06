package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear1);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodown);
//        linearLayout.setAnimation(animation);




        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainLogin.class);
                startActivity(intent);

            }
        });

        Button signUpBtn= (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent();
                intent1.setClass(MainActivity.this,SignUpPage.class);
                startActivity(intent1);
            }
        });
    }
    }