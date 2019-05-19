package com.example.prati.kachhya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_Teacher extends AppCompatActivity implements View.OnClickListener {
            private static final String LOGIN_PREFERENCES = "login";
//            FirebaseAuth mAuth;
//            TextView UserName, UserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_teacher);
        findViewById(R.id.logoutbtn).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.scanqr).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.inputassignment).setOnClickListener(Home_Teacher.this);
        findViewById(R.id.inputroutine).setOnClickListener(Home_Teacher.this);
//        UserName= (TextView)findViewById(R.id.name_teacher);
//        UserEmail= (TextView)findViewById(R.id.teacher_mail);
//        loadUserInformation();
//        mAuth.getInstance();
        }
//    private void loadUserInformation() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            if (user.getDisplayName() != null) {
//                UserName.setText(user.getDisplayName());
//            }
//            if (user.getEmail() != null) {
//                UserEmail.setText(user.getEmail());
//            }
//        }
//    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logoutbtn:
                SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(Home_Teacher.this, MainActivity.class));
                finish();
                break;
            case R.id.inputassignment:
                startActivity(new Intent(Home_Teacher.this, Assignment_add.class));
                break;
            case R.id.inputroutine:
                startActivity(new Intent(Home_Teacher.this, Routine_Add.class));
                break;
            case R.id.scanqr:
                startActivity(new Intent(Home_Teacher.this, ScanQRCode.class));
                break;
        }
    }
}
