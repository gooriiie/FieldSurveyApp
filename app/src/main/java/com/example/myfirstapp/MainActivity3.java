package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        TextView address = (TextView) findViewById(R.id.address);

        String getAddress = intent.getStringExtra("address");

        address.setText(getAddress);

        Button btn_livingroom = findViewById(R.id.button_livingroom);
        btn_livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                intent.putExtra("address", getAddress);
                intent.putExtra("room", "거실");
                startActivity(intent);
            }
        });


    }
}