package com.example.prati.kachhya;

public class Routine_Data {
    private String ID;
    private String SubjectName;
    private String TeacherName;
    private String RoomNo;
    private String Block;
    private String StartTime;
    private String EndTime;
    private String Day;

    public Routine_Data() {
    }

    public Routine_Data(String ID, String SubjectName, String TeacherName, String RoomNo, String Block, String Day, String StartTime, String EndTime) {
        this.ID = ID;
        this.SubjectName = SubjectName;
        this.TeacherName = TeacherName;
        this.RoomNo = RoomNo;
        this.Block = Block;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Day = Day;
    }

    public String getID() {
        return ID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public String getBlock() {
        return Block;
    }

    public String getStartTime() {
        return StartTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public String getDay() {
        return Day;
    }
}
