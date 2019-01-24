package com.example.prati.kachhya;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.regex.Pattern;


public class StudentSignUP extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    TextInputEditText fname, lname,email,password1, password2, phnNumber,regno;
    MaterialBetterSpinner department, year, semester, dobmonth, dobyear, dobdate,gender;
    String[] spinnerList = {"First", "Second", "Third", "Fourth"};
    String[] spinnerList1 = {"First", "Second"};
    String[] dayList = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26",
            "27","28","29","30","31"};
    String[] monthList = {"January", "February","March","April","May","June","July","August","September","October","November","December"};
    String[] yearList = {"1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996",
            "1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016",
            "2017","2018","2019"};
    String[] departlist= { "Electrical and Electronics(Communication)","Electrical and Electronics(Power and Control)","Computer Engineering", "Mechanical Engineering(Design and Manufacturing)",
                "Mechanical Engineering(Automobile)","Mechanical Engineering(Hydropower)","Mechanical Engineering(Energy Technology)","Civil Engineering","Geomatics Engineering","Bachelor of Architecture","Chemical Engineering"};
    String[] studentgender={"Male", "Female", "Others"};
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

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_sign_up);
        fname = (TextInputEditText) findViewById(R.id.fname);
        lname = (TextInputEditText) findViewById(R.id.lname);
        regno= (TextInputEditText) findViewById(R.id.regno);
        phnNumber = (TextInputEditText) findViewById(R.id.phnNumber);
        department = (MaterialBetterSpinner) findViewById(R.id.depart);
        email = (TextInputEditText) findViewById(R.id.email);
        password1 = (TextInputEditText) findViewById(R.id.password1);
        password2 = (TextInputEditText) findViewById(R.id.password2);
        year = (MaterialBetterSpinner) findViewById(R.id.year);
        semester = (MaterialBetterSpinner) findViewById(R.id.semester);
        dobyear = (MaterialBetterSpinner) findViewById(R.id.dobyear);
        dobmonth = (MaterialBetterSpinner) findViewById(R.id.dobmonth);
        dobdate = (MaterialBetterSpinner) findViewById(R.id.dobdate);
        gender= (MaterialBetterSpinner) findViewById(R.id.studentgender);
        progressBar = findViewById(R.id.ssignupprogress);
        progressBar.setVisibility(View.GONE);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,spinnerList);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.year);
        betterSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,spinnerList1);
        MaterialBetterSpinner betterSpinner1 = (MaterialBetterSpinner) findViewById(R.id.semester);
        betterSpinner1.setAdapter(arrayAdapter1);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,dayList);
        MaterialBetterSpinner betterSpinner2 = (MaterialBetterSpinner) findViewById(R.id.dobdate);
        betterSpinner2.setAdapter(arrayAdapter2);

        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,monthList);
        MaterialBetterSpinner betterSpinner3 = (MaterialBetterSpinner) findViewById(R.id.dobmonth);
        betterSpinner3.setAdapter(arrayAdapter3);

        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,yearList);
        MaterialBetterSpinner betterSpinner4 = (MaterialBetterSpinner) findViewById(R.id.dobyear);
        betterSpinner4.setAdapter(arrayAdapter4);

        ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,departlist);
        MaterialBetterSpinner betterSpinner5 = (MaterialBetterSpinner) findViewById(R.id.depart);
        betterSpinner5.setAdapter(arrayAdapter5);
        ArrayAdapter<String> arrayAdapter6 = new ArrayAdapter<String>(StudentSignUP.this,android.R.layout.simple_dropdown_item_1line,studentgender);
        MaterialBetterSpinner betterSpinner6 = (MaterialBetterSpinner) findViewById(R.id.studentgender);
        betterSpinner6.setAdapter(arrayAdapter6);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.submitBtn).setOnClickListener(StudentSignUP.this);

    }

    private void registerStudent(){
        final String fnamestr = fname.getText().toString();
        final String lnamestr = lname.getText().toString();
        final String regnostr = regno.getText().toString();
        final String emailstr = email.getText().toString().toLowerCase();
        final String departmentstr = department.getText().toString();
        String password1str = password1.getText().toString();
        String password2str = password2.getText().toString();
        final String phnNumberstr = phnNumber.getText().toString();
        final String yearstr = year.getText().toString();
        final String semesterstr = semester.getText().toString();
        final String dobmonthstr = dobmonth.getText().toString();
        final String dobyearstr = dobyear.getText().toString();
        final String dobdatestr = dobdate.getText().toString();
        final String genderstr = gender.getText().toString();
//               Checking if there is any error in input or not
        if (fnamestr.isEmpty()) {
            fname.setError(getString(R.string.error_blank));
            fname.requestFocus();
            fname.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
        if (lnamestr.isEmpty()) {
            lname.setError(getString(R.string.error_blank));
            lname.requestFocus();
            lname.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
        if (regnostr.isEmpty()) {
            regno.setError(getString(R.string.error_blank));
            regno.requestFocus();
            regno.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
        if (departmentstr.isEmpty()) {
            department.setError(getString(R.string.error_blank));
            department.requestFocus();
            return;
        }
        if (yearstr.isEmpty()) {
            year.setError(getString(R.string.error_blank));
            year.requestFocus();
            return;
        }
        if (semesterstr.isEmpty()) {
            semester.setError(getString(R.string.error_blank));
            semester.requestFocus();
            return;
        }
        if (dobdatestr.isEmpty()) {
            dobdate.setError(getString(R.string.error_blank));
            dobdate.requestFocus();
            return;
        }
        if (dobmonthstr.isEmpty()) {
            dobmonth.setError(getString(R.string.error_blank));
            dobmonth.requestFocus();
            return;
        }
        if (dobyearstr.isEmpty()) {
            dobyear.setError(getString(R.string.error_blank));
            dobyear.requestFocus();
            return;
        }
        if (genderstr.isEmpty()){
            gender.setError(getString(R.string.error_blank));
            gender.requestFocus();
            return;
        }

        if (emailstr.isEmpty()) {
            email.setError(getString(R.string.error_blank));
            email.requestFocus();
            email.setBackground(getDrawable(R.drawable.background_error));
        }
//                Check if the Email is Valid or Not
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailstr).matches()){
            email.setError("Please Enter a Valid Email Address!");
            email.requestFocus();
            email.setBackground(getDrawable(R.drawable.background_error));
        }
        //                Checks if the phone number is valid or not
        if (phnNumberstr.isEmpty()) {
            phnNumber.setError(getString(R.string.error_blank));
            phnNumber.requestFocus();
            phnNumber.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
        if (phnNumberstr.length() != 10) {
            phnNumber.setError("Enter Valid Phone Number");
            phnNumber.requestFocus();
            phnNumber.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
//                  Check whether the password is valid or not
        if (password1str.isEmpty()) {
            password1.setError(getString(R.string.error_blank));
            password1.requestFocus();
            password1.setBackground(getDrawable(R.drawable.background_error));
        }
        if(!PASSWORD_PATTERN.matcher(password1str).matches()){
            password1.setError("Password Should Contain atleast One Uppercase letter, One Number and minimum 6 digits");
            password1.requestFocus();
            password1.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
//               Check whether password and Confirm password field is same or not

        if(password2str.isEmpty()){
            password2.setError(getString(R.string.error_blank));
            password2.requestFocus();
            password2.setBackground(getDrawable(R.drawable.background_error));
            return;
        }
        if (!password1str.equals(password2str)) {
            password2.setError("Password Do not Match");
            password2.requestFocus();
            password2.setBackground(getDrawable(R.drawable.background_error));
            Toast.makeText(this, "PASSWORD DO NOT MATCH", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailstr,password1str).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Data_Student student= new Data_Student(fnamestr, lnamestr,regnostr, phnNumberstr,emailstr, departmentstr, semesterstr,yearstr, dobdatestr, dobmonthstr,dobyearstr, genderstr);
                    Data_Student student1= new Data_Student();
                    FirebaseDatabase.getInstance().getReference("Students")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Registration Failed!! Please Check your Internet Connection!!", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Registration Failed!! Please Check your Internet Connection!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitBtn:
                registerStudent();
                break;
        }
    }
}