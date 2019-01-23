package com.example.prati.kachhya;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.regex.Pattern;


public class TeacherSignUp extends AppCompatActivity implements View.OnClickListener{
    TextInputEditText fname, lname,email,password1, password2, phnNumber;
    MaterialBetterSpinner department,gender;
    private FirebaseAuth mAuth;
    String[] departlistteacher= { "Electrical and Electronics Engineering","Computer Science and Engineering", "Mechanical Engineering","Civil and Geomatics Engineering","Chemical Engineering","Natural Sciences","Environmental Science and Engineering"};
    String[] genderteacher= {"Male","Female", "Others"};
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
//                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
//                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");
    public TeacherSignUp(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_sign_up);
        TextInputEditText fname = (TextInputEditText)findViewById(R.id.txtFname);
        TextInputEditText lname = (TextInputEditText) findViewById(R.id.txtLname);
        TextInputEditText email = (TextInputEditText)findViewById(R.id.txtEmail);
        MaterialBetterSpinner department = (MaterialBetterSpinner) findViewById(R.id.txtdepart);
        TextInputEditText password1 = (TextInputEditText)findViewById(R.id.txtPass1);
        TextInputEditText password2 = (TextInputEditText)findViewById(R.id.txtPass2);
        MaterialBetterSpinner gender = (MaterialBetterSpinner) findViewById(R.id.teachergender);
        TextInputEditText phnNumber = (TextInputEditText)findViewById(R.id.txtPhone);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TeacherSignUp.this,android.R.layout.simple_dropdown_item_1line,departlistteacher);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.txtdepart);
        betterSpinner.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(TeacherSignUp.this,android.R.layout.simple_dropdown_item_1line,genderteacher);
        MaterialBetterSpinner betterSpinner1 = (MaterialBetterSpinner) findViewById(R.id.teachergender);
        betterSpinner1.setAdapter(arrayAdapter1);
        mAuth = FirebaseAuth.getInstance();
    }
    private void registerTeacher(){
        final String fnamestr = fname.getText().toString();
        final String lnamestr = lname.getText().toString();
        final String emailstr = email.getText().toString().toLowerCase();
        final String departmentstr = department.getText().toString();
        final String password1str = password1.getText().toString();
        final String password2str = password2.getText().toString();
        final String phnNumberstr = phnNumber.getText().toString();
        final String gendstr = gender.getText().toString();

//               Checking if there is any error in input or not
        if (fnamestr.isEmpty()) {
            fname.setError(getString(R.string.error_blank));
            fname.requestFocus();
            fname.setBackground(getDrawable(R.drawable.background_error));
        }
        else if (lnamestr.isEmpty()) {
            lname.setError(getString(R.string.error_blank));
            lname.requestFocus();
            lname.setBackground(getDrawable(R.drawable.background_error));
        }
        else if (departmentstr.isEmpty()) {
            department.setError(getString(R.string.error_blank));
            department.requestFocus();
            department.setBackground(getDrawable(R.drawable.background_error));
        }
        else if (gendstr.isEmpty()){
            gender.setError(getString(R.string.error_blank));
            gender.requestFocus();
        }
        else if (emailstr.isEmpty()) {
            email.setError(getString(R.string.error_blank));
            email.requestFocus();
            email.setBackground(getDrawable(R.drawable.background_error));
        }
//                Check if the Email is Valid or Not
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailstr).matches()){
            email.setError("Please Enter Valid Email Address!");
            email.requestFocus();
            email.setBackground(getDrawable(R.drawable.background_error));
        }

//                Checks if the phone number is valid or not
        else if (phnNumberstr.isEmpty()) {
            phnNumber.setError(getString(R.string.error_blank));
            phnNumber.requestFocus();
            phnNumber.setBackground(getDrawable(R.drawable.background_error));
        }
        else if (phnNumberstr.length() != 10) {
            phnNumber.setError("Enter Valid Phone Number");
            phnNumber.requestFocus();
            phnNumber.setBackground(getDrawable(R.drawable.background_error));
        }
        //                  Check whether the password is valid or not
        else if (password1str.isEmpty()) {
            password1.setError(getString(R.string.error_blank));
            password1.requestFocus();
            password1.setBackground(getDrawable(R.drawable.background_error));
        }
        else if(!PASSWORD_PATTERN.matcher(password1str).matches()){
            password1.setError("Password Should Contain atleast One Uppercase letter, One Number and minimum 6 digits");
            password1.requestFocus();
            password1.setBackground(getDrawable(R.drawable.background_error));
        }
        else if(password2str.isEmpty()){
            password2.setError(getString(R.string.error_blank));
            password2.requestFocus();
            password2.setBackground(getDrawable(R.drawable.background_error));
        }
//               Check whether password and Confirm password field is same or not
        else if (!password1str.equals(password2str)) {
            password2.setError("Password Do not Match");
            password2.requestFocus();
            password2.setBackground(getDrawable(R.drawable.background_error));
        }
        mAuth.createUserWithEmailAndPassword(emailstr,password1str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Data_Teacher teacher= new Data_Teacher(fnamestr, lnamestr, phnNumberstr,emailstr, departmentstr, gendstr);
                    FirebaseDatabase.getInstance().getReference("Teachers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(teacher).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.registerBtn:
                registerTeacher();
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

