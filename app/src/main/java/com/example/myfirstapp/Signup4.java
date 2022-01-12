package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

public class Signup4 extends AppCompatActivity {

    LinearLayout signupLayout4;
    Button btn_nextSignup5;
    TextInputEditText editTextName;
    String email, password, phoneNumber, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup4);

        Intent intent = getIntent();
        email = intent.getStringExtra("email").toString();
        password = intent.getStringExtra("password").toString();
        phoneNumber = intent.getStringExtra("phoneNumber").toString();

        signupLayout4 = findViewById(R.id.signupLayout4);
        btn_nextSignup5 = findViewById(R.id.button_nextSignup5);
        editTextName = findViewById(R.id.editTextName);

        signupLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_nextSignup5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Signup5.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}