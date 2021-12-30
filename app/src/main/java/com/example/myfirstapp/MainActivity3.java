package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    String getAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        TextView address = (TextView) findViewById(R.id.address);

        getAddress = intent.getStringExtra("address");

        address.setText(getAddress);

        Button btn_livingroom = (Button) findViewById(R.id.button_livingroom);
        Button btn_kitchen = (Button) findViewById(R.id.button_kitchen);
        Button btn_bathroom1 = (Button) findViewById(R.id.button_bathroom1);
        Button btn_bathroom2 = (Button) findViewById(R.id.button_bathroom2);
        Button btn_bedroom1 = (Button) findViewById(R.id.button_bedroom1);
        Button btn_bedroom2 = (Button) findViewById(R.id.button_bedroom2);
        Button btn_bedroom3 = (Button) findViewById(R.id.button_bedroom3);
        Button btn_pantry = (Button) findViewById(R.id.button_pantry);
        Button btn_dressroom = (Button) findViewById(R.id.button_dressroom);
        Button btn_airconditionerroom = (Button) findViewById(R.id.button_airconditionerroom);
        Button btn_boiler = (Button) findViewById(R.id.button_boiler);

        btn_livingroom.setOnClickListener(this);
        btn_kitchen.setOnClickListener(this);
        btn_bathroom1.setOnClickListener(this);
        btn_bathroom2.setOnClickListener(this);
        btn_bedroom1.setOnClickListener(this);
        btn_bedroom2.setOnClickListener(this);
        btn_bedroom3.setOnClickListener(this);
        btn_pantry.setOnClickListener(this);
        btn_dressroom.setOnClickListener(this);
        btn_airconditionerroom.setOnClickListener(this);
        btn_boiler.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
        intent.putExtra("address", getAddress);

        switch (view.getId()) {
            case R.id.button_livingroom:
                intent.putExtra("room", "거실");
                break;
            case R.id.button_kitchen:
                intent.putExtra("room", "주방");
                break;
            case R.id.button_bathroom1:
                intent.putExtra("room", "욕실1");
                break;
            case R.id.button_bathroom2:
                intent.putExtra("room", "욕실2");
                break;
            case R.id.button_bedroom1:
                intent.putExtra("room", "침실1");
                break;
            case R.id.button_bedroom2:
                intent.putExtra("room", "침실2");
                break;
            case R.id.button_bedroom3:
                intent.putExtra("room", "침실3");
                break;
            case R.id.button_pantry:
                intent.putExtra("room", "펜트리");
                break;
            case R.id.button_dressroom:
                intent.putExtra("room", "드레스룸");
                break;
            case R.id.button_airconditionerroom:
                intent.putExtra("room", "실외기실");
                break;
            case R.id.button_boiler:
                intent.putExtra("room", "보일러실");
                break;
        }
        startActivity(intent);
    }
}