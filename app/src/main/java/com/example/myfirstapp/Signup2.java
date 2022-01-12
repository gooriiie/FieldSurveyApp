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

public class Signup2 extends AppCompatActivity {

    LinearLayout signupLayout2;
    Button btn_nextSignup3;
    TextInputEditText editTextPassword;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        Intent intent = getIntent();
        email = intent.getStringExtra("email").toString();

        signupLayout2 = findViewById(R.id.signupLayout2);
        btn_nextSignup3 = findViewById(R.id.button_nextSignup3);
        editTextPassword = findViewById(R.id.editTextPassword);

        signupLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_nextSignup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = editTextPassword.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Signup3.class);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
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