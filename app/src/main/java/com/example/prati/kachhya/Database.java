package com.example.prati.kachhya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME= "Register_teacher.db";
    public static final String TABLE_NAME= "Register_teacher";
    public static final String COL_1= "ID";
    public static final String COL_2= "FirstName";
    public static final String COL_3= "LastName";
    public static final String COL_4= "Depart";
    public static final String COL_10= "Gender";
    public static final String COL_11= "Email";
    public static final String COL_12= "Phone";
    public static final String COL_13= "Password";
    SQLiteDatabase db;
    public Database(Context context) {
        super(context, "Register_teacher.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE Register_teacher(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,FirstName TEXT NOT NULL,LastName TEXT NOT NULL,Depart TEXT NOT NULL,Gender TEXT NOT NULL,Email TEXT NOT NULL, Phone TEXT NOT NULL,Password TEXT)");
        this.db=db;
    }
    public void insertdata(Data_teacher tdata){
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query= " select * from "+TABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        int count= cursor.getCount();
        values.put(COL_1,count);
        values.put(COL_2, tdata.getFname());
        values.put(COL_3, tdata.getLname());
        values.put(COL_4, tdata.getDepart());
        values.put(COL_10, tdata.getGender());
        values.put(COL_11, tdata.getEmail());
        values.put(COL_12, tdata.getPhoneNumber());
        values.put(COL_13, tdata.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public String searchPass(String email){
        db= this.getReadableDatabase();
        String query= "Select Email,Password from Register_teacher";
        Cursor cursor=db.rawQuery(query,null);
        String a,b;
        b= "Not Found";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);
                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS Register_teacher");
        this.onCreate(db);
    }
}
