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

public class Signup3 extends AppCompatActivity {

    LinearLayout signupLayout3;
    Button btn_nextSignup4;
    TextInputEditText editTextPhoneNumber;
    String email, password, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        Intent intent = getIntent();
        email = intent.getStringExtra("email").toString();
        password = intent.getStringExtra("password").toString();

        signupLayout3 = findViewById(R.id.signupLayout3);
        btn_nextSignup4 = findViewById(R.id.button_nextSignup4);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);

        signupLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_nextSignup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = editTextPhoneNumber.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Signup4.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("phoneNumber", phoneNumber);
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