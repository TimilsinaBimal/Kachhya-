package com.example.prati.kachhya;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class Assignment_add extends AppCompatActivity {
                Button selectFile ,upload;
                TextView notification;
                FirebaseStorage storage;
                FirebaseDatabase database;
                Uri pdfUri;
                ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_add);
        storage= FirebaseStorage.getInstance();  //returns an object of Firebase storage
        database= FirebaseDatabase.getInstance();    //returns an object of Firebase Database
        selectFile = findViewById(R.id.selectFile);
        upload= findViewById(R.id.upload);
        notification= findViewById(R.id.notification);
        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Assignment_add.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    selectPDF();
                }
                else{
                    ActivityCompat.requestPermissions(Assignment_add.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9 );
                }
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if(pdfUri!= null){
                uploadFile(pdfUri);
            }
            else{
                  Toast.makeText(Assignment_add.this,"Please Select a File",Toast.LENGTH_SHORT).show();
                  }
        }
        private void uploadFile(final Uri pdfUri){
            progressDialog = new ProgressDialog(Assignment_add.this);
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("Uploading File...");
            progressDialog.setProgress(0);
            progressDialog.show();
            final String fileName= System.currentTimeMillis()+".pdf";
            final String fileName1= System.currentTimeMillis()+"";
            StorageReference storageReference= storage.getReference("Assignments");
             storageReference.child("Assignments").child(fileName1).putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                     String url = Objects.requireNonNull(taskSnapshot.getUploadSessionUri()).toString();
                     DatabaseReference reference= database.getReference("Assignments");
                     reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(Assignment_add.this,"File Uploaded Successfully...",Toast.LENGTH_SHORT).show();
                             }
                             else{
                                 Toast.makeText(Assignment_add.this,"An error Occured. File is not uploaded",Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                      Toast.makeText(Assignment_add.this,"File is not Uploaded Successfully!!",Toast.LENGTH_SHORT).show();
                 }
             }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                      int currentProgress = (int)(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                      progressDialog.setProgress(currentProgress);
                 }
             });
        }
        });
    }
    private void selectPDF(){
        Intent intent = new Intent();
        intent.setType("application/pdf");
//        intent.setType("docx/*");
        intent.setAction(Intent.ACTION_GET_CONTENT); //To fetch files
        startActivityForResult(intent, 86);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
              selectPDF();
        }
        else{
            Toast.makeText(Assignment_add.this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //cHECK whether user has selected file or not
        if (requestCode==86 && resultCode== RESULT_OK && data!= null ){
             pdfUri= data.getData();
             notification.setText(Objects.requireNonNull(data.getData()).getLastPathSegment());

        }
        else{
            Toast.makeText(Assignment_add.this,"Please Select a File",Toast.LENGTH_SHORT).show();
        }
    }
}
