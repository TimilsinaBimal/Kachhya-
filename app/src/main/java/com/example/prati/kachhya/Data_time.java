package com.example.prati.kachhya;

public class Data_time {
    private String Day;
    private String StartTime;
    private  String EndTime;

    public Data_time() {

    }

    public Data_time(String Day, String StartTime, String EndTime) {
        this.Day= Day;
        this.StartTime= StartTime;
        this.EndTime= EndTime;
    }

    public String getDay() {
        return Day;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }
}
