package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    Button btn_switch1minus;
    Button btn_switch1plus;
    Button btn_switch2minus;
    Button btn_switch2plus;
    Button btn_switch3minus;
    Button btn_switch3plus;
    Button btn_switch4minus;
    Button btn_switch4plus;
    Button btn_switch5minus;
    Button btn_switch5plus;
    Button btn_switch6minus;
    Button btn_switch6plus;

    Button btn_socket1minus;
    Button btn_socket1plus;
    Button btn_socket2minus;
    Button btn_socket2plus;
    Button btn_socket4minus;
    Button btn_socket4plus;

    Button btn_sensor1minus;
    Button btn_sensor1plus;
    Button btn_sensor2minus;
    Button btn_sensor2plus;

    Button btn_scrollTo_switchLine;
    Button btn_scrollTo_sensorLine;
    Button btn_scrollTo_socketLine;
    Button btn_scrollTo_lightLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();

        TextView addressroom = (TextView) findViewById(R.id.addressroom);

        String getAddress = intent.getStringExtra("address");
        String getRoom = intent.getStringExtra("room");
        String getAddressRoom = getAddress + "/" + getRoom;

        addressroom.setText(getAddressRoom);

        btn_switch1minus = (Button) findViewById(R.id.button_switch1minus);
        btn_switch1plus = (Button) findViewById(R.id.button_switch1plus);
        btn_switch2minus = (Button) findViewById(R.id.button_switch2minus);
        btn_switch2plus = (Button) findViewById(R.id.button_switch2plus);
        btn_switch3minus = (Button) findViewById(R.id.button_switch3minus);
        btn_switch3plus = (Button) findViewById(R.id.button_switch3plus);
        btn_switch4minus = (Button) findViewById(R.id.button_switch4minus);
        btn_switch4plus = (Button) findViewById(R.id.button_switch4plus);
        btn_switch5minus = (Button) findViewById(R.id.button_switch5minus);
        btn_switch5plus = (Button) findViewById(R.id.button_switch5plus);
        btn_switch6minus = (Button) findViewById(R.id.button_switch6minus);
        btn_switch6plus = (Button) findViewById(R.id.button_switch6plus);

        btn_socket1minus = (Button) findViewById(R.id.button_socket1minus);
        btn_socket1plus = (Button) findViewById(R.id.button_socket1plus);
        btn_socket2minus = (Button) findViewById(R.id.button_socket2minus);
        btn_socket2plus = (Button) findViewById(R.id.button_socket2plus);
        btn_socket4minus = (Button) findViewById(R.id.button_socket4minus);
        btn_socket4plus = (Button) findViewById(R.id.button_socket4plus);

        btn_sensor1minus = (Button) findViewById(R.id.button_sensor1minus);
        btn_sensor1plus = (Button) findViewById(R.id.button_sensor1plus);
        btn_sensor2minus = (Button) findViewById(R.id.button_sensor2minus);
        btn_sensor2plus = (Button) findViewById(R.id.button_sensor2plus);

        btn_scrollTo_switchLine = (Button) findViewById(R.id.button_scrollTo_switchLine);
        btn_scrollTo_socketLine = (Button) findViewById(R.id.button_scrollTo_socketLine);
        btn_scrollTo_sensorLine = (Button) findViewById(R.id.button_scrollTo_sensorLine);
        btn_scrollTo_lightLine = (Button) findViewById(R.id.button_scrollTo_lightLine);

        btn_switch1minus.setOnClickListener(this);
        btn_switch1plus.setOnClickListener(this);
        btn_switch2minus.setOnClickListener(this);
        btn_switch2plus.setOnClickListener(this);
        btn_switch3minus.setOnClickListener(this);
        btn_switch3plus.setOnClickListener(this);
        btn_switch4minus.setOnClickListener(this);
        btn_switch4plus.setOnClickListener(this);
        btn_switch5minus.setOnClickListener(this);
        btn_switch5plus.setOnClickListener(this);
        btn_switch6minus.setOnClickListener(this);
        btn_switch6plus.setOnClickListener(this);

        btn_socket1minus.setOnClickListener(this);
        btn_socket1plus.setOnClickListener(this);
        btn_socket2minus.setOnClickListener(this);
        btn_socket2plus.setOnClickListener(this);
        btn_socket4minus.setOnClickListener(this);
        btn_socket4plus.setOnClickListener(this);

        btn_sensor1minus.setOnClickListener(this);
        btn_sensor1plus.setOnClickListener(this);
        btn_sensor2minus.setOnClickListener(this);
        btn_sensor2plus.setOnClickListener(this);

        btn_scrollTo_switchLine.setOnClickListener(this);
        btn_scrollTo_socketLine.setOnClickListener(this);
        btn_scrollTo_sensorLine.setOnClickListener(this);
        btn_scrollTo_lightLine.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        ScrollView sv = (ScrollView) findViewById(R.id.scroll);

        TextView switch1count = (TextView) findViewById(R.id.switch1count);
        TextView switch2count = (TextView) findViewById(R.id.switch2count);
        TextView switch3count = (TextView) findViewById(R.id.switch3count);
        TextView switch4count = (TextView) findViewById(R.id.switch4count);
        TextView switch5count = (TextView) findViewById(R.id.switch5count);
        TextView switch6count = (TextView) findViewById(R.id.switch6count);

        TextView socket1count = (TextView) findViewById(R.id.socket1count);
        TextView socket2count = (TextView) findViewById(R.id.socket2count);
        TextView socket4count = (TextView) findViewById(R.id.socket4count);

        TextView sensor1count = (TextView) findViewById(R.id.sensor1count);
        TextView sensor2count = (TextView) findViewById(R.id.sensor2count);

        String count;
        int count_int;

        switch (view.getId()) {
            case R.id.button_switch1minus:
                count = switch1count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch1count.setText(count);
                }
                break;
            case R.id.button_switch1plus:
                count = switch1count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch1count.setText(count);
                break;
            case R.id.button_switch2minus:
                count = switch2count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch2count.setText(count);
                }
                break;
            case R.id.button_switch2plus:
                count = switch2count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch2count.setText(count);
                break;
            case R.id.button_switch3minus:
                count = switch3count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch3count.setText(count);
                }
                break;
            case R.id.button_switch3plus:
                count = switch3count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch3count.setText(count);
                break;
            case R.id.button_switch4minus:
                count = switch4count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch4count.setText(count);
                }
                break;
            case R.id.button_switch4plus:
                count = switch4count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch4count.setText(count);
                break;
            case R.id.button_switch5minus:
                count = switch5count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch5count.setText(count);
                }
                break;
            case R.id.button_switch5plus:
                count = switch5count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch5count.setText(count);
                break;
            case R.id.button_switch6minus:
                count = switch6count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    switch6count.setText(count);
                }
                break;
            case R.id.button_switch6plus:
                count = switch6count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                switch6count.setText(count);
                break;
            case R.id.button_socket1minus:
                count = socket1count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    socket1count.setText(count);
                }
                break;
            case R.id.button_socket1plus:
                count = socket1count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                socket1count.setText(count);
                break;
            case R.id.button_socket2minus:
                count = socket2count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    socket2count.setText(count);
                }
                break;
            case R.id.button_socket2plus:
                count = socket2count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                socket2count.setText(count);
                break;
            case R.id.button_socket4minus:
                count = socket4count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    socket4count.setText(count);
                }
                break;
            case R.id.button_socket4plus:
                count = socket4count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                socket4count.setText(count);
                break;
            case R.id.button_sensor1minus:
                count = sensor1count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    sensor1count.setText(count);
                }
                break;
            case R.id.button_sensor1plus:
                count = sensor1count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                sensor1count.setText(count);
                break;
            case R.id.button_sensor2minus:
                count = sensor2count.getText().toString();
                count_int = Integer.parseInt(count);
                if (count_int <= 0) {
                    makeText(getApplicationContext(), "개수가 0개입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    count_int--;
                    count = Integer.toString(count_int);
                    sensor2count.setText(count);
                }
                break;
            case R.id.button_sensor2plus:
                count = sensor2count.getText().toString();
                count_int = Integer.parseInt(count);
                count_int++;
                count = Integer.toString(count_int);
                sensor2count.setText(count);
                break;

            case R.id.button_scrollTo_switchLine:
                sv.scrollTo(0,0);
                break;
            case R.id.button_scrollTo_socketLine:
                sv.scrollTo(0, 750);
                break;
            case R.id.button_scrollTo_sensorLine:
                sv.scrollTo(0, 1170);
                break;
            case R.id.button_scrollTo_lightLine:
                sv.scrollTo(0, 1450);
                break;
        }
    }
}