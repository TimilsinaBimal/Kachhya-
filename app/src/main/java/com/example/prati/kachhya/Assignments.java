package com.example.prati.kachhya;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Objects;

public class Assignments extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Assignments");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    // called for individual items at database reference
                String filename= dataSnapshot.getKey();
                String url= dataSnapshot.getValue(String.class);
                ((AssignmentAdapter) Objects.requireNonNull(recyclerView.getAdapter())).update(filename,url);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        RecyclerView recyclerView= findViewById(R.id.assignmentlists);
        recyclerView.setLayoutManager(new LinearLayoutManager(Assignments.this));
        AssignmentAdapter assignmentadapter = new AssignmentAdapter(recyclerView, Assignments.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(assignmentadapter);
    }
}
