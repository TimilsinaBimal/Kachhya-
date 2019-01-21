package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class StudentLogin extends AppCompatActivity{
    Button StudentLogin_btn;
    Database_Student Student_Data= new Database_Student(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
    }
    public void OnClickStudentLogin(View v){
        if(v.getId() == R.id.StudentLogin_btn){
            TextInputEditText a = (TextInputEditText) findViewById(R.id.student_email);
            TextInputEditText b = ( TextInputEditText) findViewById(R.id.student_password);
            String email = a.getText().toString().toLowerCase();
            String pass = b.getText().toString();
            String password= Student_Data.searchPass(email);
            if(email.isEmpty()){
                a.setError(getString(R.string.error_blank));
                a.requestFocus();
                a.setBackground(getDrawable(R.drawable.background_error));
            }
            else if(pass.isEmpty()){
                b.setError(getString(R.string.error_blank));
                b.requestFocus();
                b.setBackground(getDrawable(R.drawable.background_error));
            }
            else if(pass.equals(password))
            {
                Intent intent= new Intent(StudentLogin.this,home_page.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "INCORRECT EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
            }

        }
        Button SignupStudent= (Button)findViewById(R.id.StudentsignUpBtn);
        SignupStudent.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent2= new Intent(StudentLogin.this,StudentSignUP.class);
                                          startActivity(intent2);
                                      }
                                  }
        );
    }
}
