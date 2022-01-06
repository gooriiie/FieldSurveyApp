package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore db;
    private Map<String, Integer> _switch;
    private Map<String, Integer> _socket;
    private Map<String, Integer> _sensor;

    String getAddress;
    String getNickName;
    String getRoom;

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

    Button btn_add;

    ArrayAdapter<CharSequence> switchSpin;

    String switchCorp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();

        TextView addressroom = (TextView) findViewById(R.id.addressroom);

        getAddress = intent.getStringExtra("address");
        getNickName = intent.getStringExtra("nickName");
        getRoom = intent.getStringExtra("room");
        String getAddressRoom = getAddress + "/" + getRoom;

        addressroom.setText(getAddressRoom);

        final Spinner switchSpinner = (Spinner) findViewById(R.id.switchSpinner);
        switchSpin = ArrayAdapter.createFromResource(this, R.array.spinner_switch_corp, android.R.layout.simple_spinner_dropdown_item);
        switchSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        switchSpinner.setAdapter(switchSpin);
        switchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switchCorp = switchSpin.getItem(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

        btn_add = (Button) findViewById(R.id.button_add);

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

        btn_add.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        _switch = new HashMap<>();
        _socket = new HashMap<>();
        _sensor = new HashMap<>();

        ScrollView sv = (ScrollView) findViewById(R.id.scroll);

        TextView switch1 = (TextView) findViewById(R.id.switch1);
        TextView switch2 = (TextView) findViewById(R.id.switch2);
        TextView switch3 = (TextView) findViewById(R.id.switch3);
        TextView switch4 = (TextView) findViewById(R.id.switch4);
        TextView switch5 = (TextView) findViewById(R.id.switch5);
        TextView switch6 = (TextView) findViewById(R.id.switch6);

        TextView switch1count = (TextView) findViewById(R.id.switch1count);
        TextView switch2count = (TextView) findViewById(R.id.switch2count);
        TextView switch3count = (TextView) findViewById(R.id.switch3count);
        TextView switch4count = (TextView) findViewById(R.id.switch4count);
        TextView switch5count = (TextView) findViewById(R.id.switch5count);
        TextView switch6count = (TextView) findViewById(R.id.switch6count);

        _switch.put(switch1.getText().toString(), Integer.parseInt(switch1count.getText().toString()));
        _switch.put(switch2.getText().toString(), Integer.parseInt(switch2count.getText().toString()));
        _switch.put(switch3.getText().toString(), Integer.parseInt(switch3count.getText().toString()));
        _switch.put(switch4.getText().toString(), Integer.parseInt(switch4count.getText().toString()));
        _switch.put(switch5.getText().toString(), Integer.parseInt(switch5count.getText().toString()));
        _switch.put(switch6.getText().toString(), Integer.parseInt(switch6count.getText().toString()));

        TextView socket1 = (TextView) findViewById(R.id.socket1);
        TextView socket2 = (TextView) findViewById(R.id.socket2);
        TextView socket4 = (TextView) findViewById(R.id.socket4);

        TextView socket1count = (TextView) findViewById(R.id.socket1count);
        TextView socket2count = (TextView) findViewById(R.id.socket2count);
        TextView socket4count = (TextView) findViewById(R.id.socket4count);

        _socket.put(socket1.getText().toString(), Integer.parseInt(socket1count.getText().toString()));
        _socket.put(socket2.getText().toString(), Integer.parseInt(socket2count.getText().toString()));
        _socket.put(socket4.getText().toString(), Integer.parseInt(socket4count.getText().toString()));

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

            // 추가하기 버튼 눌렀을 경우
            case R.id.button_add:
                Map<String, Object> room = new HashMap<>();
                Map<String, Integer> part = new HashMap<>();

                _switch.forEach((key, value) -> {
                    if(value > 0) {
                        String tmp = "(" + switchCorp + ") " + key;
                        part.put(tmp, value);
                    }
                });

                _socket.forEach((key, value) -> {
                    if(value > 0) {
                        part.put(key, value);
                    }
                });

                room.put(getRoom, part);
                room.put("닉네임", getNickName);

                db.collection("addresses").document(getAddress)
                        .set(room, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                makeText(getApplicationContext(), getRoom + "을 추가했습니다.", Toast.LENGTH_SHORT).show();

                                // intent activity3 추가
//                                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                                Intent intent = new Intent(getApplicationContext(), SelectSpace.class);
                                intent.putExtra("address", getAddress);
                                intent.putExtra("nickName", getNickName);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                makeText(getApplicationContext(), "추가하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
        }
    }
}