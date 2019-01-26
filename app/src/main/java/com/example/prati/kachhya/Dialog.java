package com.example.prati.kachhya;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public class Dialog extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        getActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.dialog_ok).setOnClickListener(Dialog.this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_ok:
                startActivity(new Intent(Dialog.this, MainLogin.class));
                break;
        }
    }
}
