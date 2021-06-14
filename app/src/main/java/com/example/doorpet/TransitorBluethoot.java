package com.example.doorpet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


public class TransitorBluethoot extends AppCompatActivity {

    private final int DURACION_SPLASH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_transitor_bluethoot);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(TransitorBluethoot.this, homescreen.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);

    }
}
