package com.example.prati.kachhya;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class Routine extends AppCompatActivity{
       ListView ListViewRoutine;
       DatabaseReference databaseReference;
       List<Routine_Data>Routinedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
            ListViewRoutine= (ListView)findViewById(R.id.routinelist);
            Routinedata= new ArrayList<>();
            databaseReference= FirebaseDatabase.getInstance().getReference("Routine");

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Routinedata.clear();
                for(DataSnapshot RoutineSnapshot: dataSnapshot.getChildren()){
                    Routine_Data data= RoutineSnapshot.getValue(Routine_Data.class);
                    Routinedata.add(data);
                }
                Routine_List adapter= new Routine_List(Routine.this,Routinedata);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
