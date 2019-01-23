package com.example.prati.kachhya;

public class Data_Student {

    public String fname, lname, regno, phoneNumber, email, depart, semester, year, dobday, dobmonth, dobyear, gender;

    public Data_Student() {

    }

    public Data_Student(String fname, String lname,String regno, String phoneNumber, String email, String depart, String semester, String year, String dobday, String dobmonth, String dobyear, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.regno= regno;
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