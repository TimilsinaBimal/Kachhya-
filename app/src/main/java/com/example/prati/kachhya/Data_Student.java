package com.example.prati.kachhya;

public class Data_Student {

    public String fname, lname, phoneNumber, email, password, depart, semester, year, dobday, dobmonth, dobyear, gender;

    public Data_Student() {

    }

    public Data_Student(String fname, String lname, String phoneNumber, String email, String depart, String semester, String year, String dobday, String dobmonth, String dobyear, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.depart = depart;
        this.semester = semester;
        this.year = year;
        this.dobyear = dobyear;
        this.dobmonth = dobmonth;
        this.dobday = dobday;
        this.gender = gender;
    }
}