package com.scan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.scan.common.Tutorial;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIMER = 5000;
    SharedPreferences tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 5000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tutorial = getSharedPreferences("tutorial", MODE_PRIVATE);
                boolean isFirstTime = tutorial.getBoolean("primeraVez",true);
                if(isFirstTime){
                    SharedPreferences.Editor editor = tutorial.edit();
                    editor.putBoolean("primeraVez",false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), Tutorial.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        },SPLASH_TIMER);
    }
}