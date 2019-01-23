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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class TeacherLogin extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar progressBar;
    Button TeacherLogin_btn;
    FirebaseAuth mAuth;
    TextInputEditText tmail,tpass;
        @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
        mAuth= FirebaseAuth.getInstance();
        tmail = (TextInputEditText) findViewById(R.id.teacher_email);
        tpass = ( TextInputEditText) findViewById(R.id.teacher_password);
        progressBar = findViewById(R.id.tloginprogress);
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.TeacherLogin_btn).setOnClickListener(this);
    }
    private void TeacherLogin(){
        String temail = tmail.getText().toString().toLowerCase();
        String tpassword = tpass.getText().toString();
        if (temail.isEmpty()) {
            tmail.setError(getString(R.string.error_blank));
            tmail.requestFocus();
        }
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(temail).matches()){
            tmail.setError("Please Enter a Valid Email Address!");
            tmail.requestFocus();
            tmail.setBackground(getDrawable(R.drawable.background_error));
        }

        if (tpassword.isEmpty()) {
            tpass.setError(getString(R.string.error_blank));
            tpass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(temail,tpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    finish();
                    Intent intent= new Intent(TeacherLogin.this,home_page.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.TeacherLogin_btn:
                TeacherLogin();
                break;
        }
        }
}
