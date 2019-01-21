package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.regex.Pattern;
public class StudentSignUP extends AppCompatActivity {
    Database_Student Student_Data =new Database_Student(this);
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

    }

    public void OnClickStudentSignUp(View v) {
        if (v.getId() == R.id.submitBtn) {
            TextInputEditText fname = (TextInputEditText) findViewById(R.id.fname);
            TextInputEditText lname = (TextInputEditText) findViewById(R.id.lname);
            MaterialBetterSpinner department = (MaterialBetterSpinner) findViewById(R.id.depart);
            TextInputEditText email = (TextInputEditText) findViewById(R.id.email);
            TextInputEditText password1 = (TextInputEditText) findViewById(R.id.password1);
            TextInputEditText password2 = (TextInputEditText) findViewById(R.id.password2);
            MaterialBetterSpinner year = (MaterialBetterSpinner) findViewById(R.id.year);
            MaterialBetterSpinner semester = (MaterialBetterSpinner) findViewById(R.id.semester);
            MaterialBetterSpinner dobyear = (MaterialBetterSpinner) findViewById(R.id.dobyear);
            MaterialBetterSpinner dobmonth = (MaterialBetterSpinner) findViewById(R.id.dobmonth);
            MaterialBetterSpinner dobdate = (MaterialBetterSpinner) findViewById(R.id.dobdate);
            RadioButton gendMale = (RadioButton) findViewById(R.id.gendMale);
            RadioButton gendFemale = (RadioButton) findViewById(R.id.gendFemale);
            RadioButton gendOthers = (RadioButton) findViewById(R.id.gendOthers);
            TextInputEditText phnNumber = (TextInputEditText) findViewById(R.id.phnNumber);
//              To get the inputted data
            String fnamestr = fname.getText().toString();
            String lnamestr = lname.getText().toString();
            String emailstr = email.getText().toString().toLowerCase();
            String departmentstr = department.getText().toString();
            String password1str = password1.getText().toString();
            String password2str = password2.getText().toString();
            String phnNumberstr = phnNumber.getText().toString();
            String yearstr = year.getText().toString();
            String semesterstr = semester.getText().toString();
            String dobmonthstr = dobmonth.getText().toString();
            String dobyearstr = dobyear.getText().toString();
            String dobdatestr = dobdate.getText().toString();
            String gender = "";
            if (gendMale.isChecked()) {
                gender = "Male";
            } else if (gendFemale.isChecked()) {
                gender = "Female";
            } else {
                gender = "Other";
            }
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
            }
            else if (yearstr.isEmpty()) {
                year.setError(getString(R.string.error_blank));
                year.requestFocus();
            }
            else if (semesterstr.isEmpty()) {
                semester.setError(getString(R.string.error_blank));
                semester.requestFocus();
            }
            else if (dobdatestr.isEmpty()) {
                dobdate.setError(getString(R.string.error_blank));
                dobdate.requestFocus();
            }
            else if (dobmonthstr.isEmpty()) {
                dobmonth.setError(getString(R.string.error_blank));
                dobmonth.requestFocus();
            }
            else if (dobyearstr.isEmpty()) {
                dobyear.setError(getString(R.string.error_blank));
                dobyear.requestFocus();
            }
            else if (gender.isEmpty()){
                gendOthers.setError(getString(R.string.error_blank));
                gendOthers.setBackground(getDrawable(R.drawable.background_error));
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
//               Check whether password and Confirm password field is same or not

            else if(password2str.isEmpty()){
                password2.setError(getString(R.string.error_blank));
                password2.requestFocus();
                password2.setBackground(getDrawable(R.drawable.background_error));
            }
            else if (!password1str.equals(password2str)) {
                password2.setError("Password Do not Match");
                password2.requestFocus();
                password2.setBackground(getDrawable(R.drawable.background_error));
                Toast.makeText(this, "PASSWORD DO NOT MATCH", Toast.LENGTH_SHORT).show();
            }

//                Storing the data into SQlite Database_Teacher
            else {
                Data_Student dataStudent = new Data_Student();
                dataStudent.setFname(fnamestr);
                dataStudent.setLname(lnamestr);
                dataStudent.setEmail(emailstr);
                dataStudent.setDepart(departmentstr);
                dataStudent.setPhoneNumber(phnNumberstr);
                dataStudent.setYear(yearstr);
                dataStudent.setSemester(semesterstr);
                dataStudent.setDobyear(dobyearstr);
                dataStudent.setDobday(dobdatestr);
                dataStudent.setDobmonth(dobmonthstr);
                dataStudent.setPassword(password1str);
                dataStudent.setGender(gender);
                Student_Data.insertdata(dataStudent);
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentSignUP.this, MainLogin.class);
                startActivity(intent);
            }
        }
    }
}