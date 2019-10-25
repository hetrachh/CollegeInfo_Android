package com.example.hetrachh.collegeinformation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeint = new Intent(MainActivity.this,SelectActivity.class);
                /*homeint.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
                startActivity(homeint);
                finish();
            }
        },SPLASH_TIME_OUT);


    }

}
