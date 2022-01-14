package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLogin extends AppCompatActivity {

    Button btn_emailLogin, btn_signup;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);

        // 자동로그인에 필요한 기능. SharedPreference 나중에 수정해야할 부분
        email = My_Shared_Preference.getUserEmail(getApplicationContext());
        password = My_Shared_Preference.getUserPassword(getApplicationContext());
        if (email != null && password != null) {
            new Login().login(email, password);
        }

        btn_emailLogin = findViewById(R.id.button_emailLogin);
        btn_signup = findViewById(R.id.button_signup);

        btn_emailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup1.class);
                startActivity(intent);
            }
        });

    }
}