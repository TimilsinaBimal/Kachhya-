package com.example.prati.kachhya;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class QR_CODE extends AppCompatActivity {
    private static final String LOGIN_PREFERENCES = "login";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr__code);
        imageView = findViewById(R.id.imageView);

        SharedPreferences sharedpreferences = getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        final String id = sharedpreferences.getString("id", "null");

        new Thread(new Runnable() {
            public void run() {
                try {
                    final URL url = new URL("https://api.qrserver.com/v1/create-qr-code/?data=" + id + "&size=220x220&margin=0");
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bmp);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("MCCC", id);
            }
        }).start();

    }
}
