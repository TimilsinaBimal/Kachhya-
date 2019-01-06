package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainLogin extends AppCompatActivity{
    Button Login_btn;
    DatabaseHelper openHelper= new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
    }
    public void OnClickLogin(View v){
        if(v.getId() == R.id.Login_btn){
          TextInputEditText a = (TextInputEditText) findViewById(R.id.txt_email);
            TextInputEditText b = ( TextInputEditText) findViewById(R.id.txt_password);
            String email = a.getText().toString().toLowerCase();
            String pass = b.getText().toString();
            String password= openHelper.searchPass(email);
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
                Intent intent= new Intent(MainLogin.this,home_page.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "INCORRECT EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
            }

        }
        Button Signup= (Button)findViewById(R.id.signUpBtn);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainLogin.this,SignUpPage.class);
                startActivity(intent);
            }
        }
        );
    }
}
