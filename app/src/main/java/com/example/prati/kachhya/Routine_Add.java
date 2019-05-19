package com.example.prati.kachhya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Routine_Add extends AppCompatActivity{
    String[] DayList = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
     MaterialBetterSpinner Day;
     EditText StartTime , EndTime;
    EditText SubjectName, TeacherName, RoomNo, Block;
    Button Save, Delete;
    DatabaseReference databaseRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_add);
        databaseRoutine = FirebaseDatabase.getInstance().getReference("Routine");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Routine_Add.this, android.R.layout.simple_dropdown_item_1line, DayList);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.subjectday);
        betterSpinner.setAdapter(arrayAdapter);
        //getting views
        SubjectName = (EditText) findViewById(R.id.subname);
        TeacherName = (EditText) findViewById(R.id.name_teacher);
        RoomNo = (EditText) findViewById(R.id.room);
        Block = (EditText) findViewById(R.id.block);
        Save = (Button) findViewById(R.id.savebutton);
        Delete = (Button) findViewById(R.id.deletebutton);
        Day= (MaterialBetterSpinner)findViewById(R.id.subjectday);
        StartTime= (EditText)findViewById(R.id.start_time);
        EndTime= (EditText)findViewById(R.id.end_time);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoutine();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    deleteRoutine();
            }
        });
    }
    private void addRoutine() {
        //getting the values to save
        String subjectname = SubjectName.getText().toString();
        String teachername = TeacherName.getText().toString();
        String room = RoomNo.getText().toString();
        String block = Block.getText().toString();
        String starttime = StartTime.getText().toString();
        String endtime = EndTime.getText().toString();
        String day = Day.getText().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(subjectname)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseRoutine.push().getKey();

            //creating an Artist Object
            Routine_Data routine_data = new Routine_Data(id, subjectname, teachername, room, block,day,starttime,endtime);

            //Saving the Artist
            databaseRoutine.child(id).setValue(routine_data);

            //setting edittext to blank again
            TeacherName.setText("");
            SubjectName.setText("");
            Block.setText("");
            RoomNo.setText("");
            StartTime.setText("");
            EndTime.setText("");

            //displaying a success toast
            Toast.makeText(this, "Routine Added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "An error Occured!! Recheck the details..", Toast.LENGTH_LONG).show();
        }
    }
}
