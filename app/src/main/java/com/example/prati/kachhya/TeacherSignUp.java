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


public class TeacherSignUp extends AppCompatActivity{
    Database_Teacher thelper =new Database_Teacher(this);
    String[] departlistteacher= { "Electrical and Electronics Engineering","Computer Science and Engineering", "Mechanical Engineering","Civil and Geomatics Engineering","Chemical Engineering","Natural Sciences","Environmental Science and Engineering"};
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
        setContentView(R.layout.teacher_sign_up);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TeacherSignUp.this,android.R.layout.simple_dropdown_item_1line,departlistteacher);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.txtdepart);
        betterSpinner.setAdapter(arrayAdapter);
    }

    public void OnClickTeacherSignUp(View v){
        if (v.getId() == R.id.registerBtn){
            TextInputEditText fname = (TextInputEditText)findViewById(R.id.txtFname);
            TextInputEditText lname = (TextInputEditText) findViewById(R.id.txtLname);
            TextInputEditText email = (TextInputEditText)findViewById(R.id.txtEmail);
            MaterialBetterSpinner department = (MaterialBetterSpinner) findViewById(R.id.txtdepart);
            TextInputEditText password1 = (TextInputEditText)findViewById(R.id.txtPass1);
            TextInputEditText password2 = (TextInputEditText)findViewById(R.id.txtPass2);
            RadioButton gendMale =(RadioButton)findViewById(R.id.TgendMale);
            RadioButton gendFemale =(RadioButton)findViewById(R.id.TgendFemale);
            RadioButton gendOthers =(RadioButton)findViewById(R.id.TgendOthers);
            TextInputEditText phnNumber = (TextInputEditText)findViewById(R.id.txtPhone);
//          Getting the entered text
            String fnamestr = fname.getText().toString();
            String lnamestr = lname.getText().toString();
            String emailstr = email.getText().toString().toLowerCase();
            String departmentstr = department.getText().toString();
            String password1str = password1.getText().toString();
            String password2str = password2.getText().toString();
            String phnNumberstr = phnNumber.getText().toString();
            String gendstr="";
            if(gendFemale.isChecked()){
                gendstr="Female";
            }
            else if(gendMale.isChecked()){
                gendstr="Male";
            }
            else{
                gendstr="Other";
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
                department.setBackground(getDrawable(R.drawable.background_error));
            }
            else if (gendstr.isEmpty()){
                gendOthers.setError(getString(R.string.error_blank));
                gendOthers.requestFocus();
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
            else {
                Data_Teacher tdata = new Data_Teacher();
                tdata.setFname(fnamestr);
                tdata.setLname(lnamestr);
                tdata.setEmail(emailstr);
                tdata.setDepart(departmentstr);
                tdata.setPhoneNumber(phnNumberstr);
                tdata.setGender(gendstr);
                tdata.setPassword(password1str);
                thelper.insertdata(tdata);
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TeacherSignUp.this, MainLogin.class);
                startActivity(intent);
            }
        }
    }
}
