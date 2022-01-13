package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextInputEditText id, password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.loginId);
        password = findViewById(R.id.loginPassword);
        btn_login = findViewById(R.id.button_login);
//        btn_findPassword = findViewById(R.id.button_findPassword);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db 확인 후 id, pw 일치하면 login 성공
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("id", id.getText().toString());
//                intent.putExtra("password", password.getText().toString());
//                startActivity(intent);
            }
        });
    }
}