package com.example.hetrachh.collegeinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    Button intsem,regsem,ica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        intsem = (Button) findViewById(R.id.mcas1);
        regsem = (Button) findViewById(R.id.mcas3);
        ica = (Button) findViewById(R.id.icac);

        intsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent imca1 = new Intent(SelectActivity.this,IntegratedActivity.class);
                startActivity(imca1);
                finish();
            }
        });

        regsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mca1 = new Intent(SelectActivity.this,HomeActivity.class);
                startActivity(mca1);
                finish();
            }
        });

        ica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ic = new Intent(SelectActivity.this,HomeActivity.class);
                startActivity(ic);
                finish();
            }
        });
    }
}
