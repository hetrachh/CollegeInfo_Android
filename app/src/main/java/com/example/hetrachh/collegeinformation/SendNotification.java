package com.example.hetrachh.collegeinformation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SendNotification extends AppCompatActivity {

    Button Rmca1,Rmca3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        Rmca1 = (Button) findViewById(R.id.mcas1);
        Rmca3 = (Button) findViewById(R.id.mcas3);

    }



}
