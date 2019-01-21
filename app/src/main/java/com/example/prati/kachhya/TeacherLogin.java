package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TeacherLogin extends AppCompatActivity{
    Button TeacherLogin_btn;
    Button TeacherSignupbtn;
    Database_Teacher Teacher_Data= new Database_Teacher(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
    }
    public void OnClickTeacherLogin(View v){
        if(v.getId() == R.id.TeacherLogin_btn){
            TextInputEditText c = (TextInputEditText) findViewById(R.id.teacher_email);
            TextInputEditText d = ( TextInputEditText) findViewById(R.id.teacher_password);
            String email = c.getText().toString().toLowerCase();
            String pass = d.getText().toString();
            String password= Teacher_Data.searchPass(email);
            if(email.isEmpty()){
                c.setError(getString(R.string.error_blank));
                c.requestFocus();
                c.setBackground(getDrawable(R.drawable.background_error));
            }
            else if(pass.isEmpty()){
                d.setError(getString(R.string.error_blank));
                d.requestFocus();
                d.setBackground(getDrawable(R.drawable.background_error));
            }
            else if(pass.equals(password))
            {
                Intent intent= new Intent(TeacherLogin.this,home_page.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "INCORRECT EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
            }

        }
        TeacherSignupbtn = (Button)findViewById(R.id.teachersignUpBtn);
        TeacherSignupbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent2= new Intent(TeacherLogin.this,TeacherSignUp.class);
                                          startActivity(intent2);
                                      }
                                  }
        );
    }
}
