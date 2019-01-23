package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StudentLogin extends AppCompatActivity implements View.OnClickListener {
    Button StudentLogin_btn;
    FirebaseAuth mAuth;
    TextInputEditText mail, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        mAuth = FirebaseAuth.getInstance();
        mail= (TextInputEditText) findViewById(R.id.student_email);
        pass = ( TextInputEditText) findViewById(R.id.student_password);
        findViewById(R.id.StudentLogin_btn).setOnClickListener(this);
    }
    private void StudentLogin(){
        String email = mail.getText().toString().toLowerCase();
        String password = pass.getText().toString();
        if (email.isEmpty()) {
            mail.setError("Email is required");
            mail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    finish();
                    Intent intent= new Intent(StudentLogin.this,home_page.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        if (mAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(this,home_page.class));
//        }
//    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.StudentLogin_btn:
                StudentLogin();
                break;
        }
    }
}