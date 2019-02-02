package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StudentLogin extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    Button StudentLogin_btn;
    FirebaseAuth mAuth;
    TextInputEditText mail, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        FirebaseApp.initializeApp(StudentLogin.this);
        mAuth = FirebaseAuth.getInstance();
        mail= (TextInputEditText) findViewById(R.id.student_email);
        pass = ( TextInputEditText) findViewById(R.id.student_password);
        progressBar = findViewById(R.id.sloginprogress);
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.StudentLogin_btn).setOnClickListener(this);
    }
    private void StudentLogin(){
        String email = mail.getText().toString().toLowerCase();
        String password = pass.getText().toString();
        if (email.isEmpty()) {
            mail.setError(getString(R.string.error_blank));
            mail.requestFocus();
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("Please Enter a Valid Email Address!");
            mail.requestFocus();
            mail.setBackground(getDrawable(R.drawable.background_error));
        }

        if (password.isEmpty()) {
            pass.setError(getString(R.string.error_blank));
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    finish();
                    Intent intent= new Intent(StudentLogin.this,Home_Student.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login Failed!! Please Check your Internet Connection!!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//        if (mAuth.getCurrentUser()!= null){
//            finish();
//            startActivity(new Intent(this,MainLogin.class));
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