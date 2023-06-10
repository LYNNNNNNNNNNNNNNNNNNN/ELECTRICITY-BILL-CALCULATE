package com.electricity.bill_calculate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class Splash_Activity extends AppCompatActivity {

    ImageView splash_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_logo = findViewById(R.id.imgSplash);


        //For Splash Screen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Code here

                Intent myIntent = new Intent(Splash_Activity.this, MainActivity.class);
                startActivity(myIntent);
                finish();

            }
        },3000);
    }
}

// This app develop by NOOR :)