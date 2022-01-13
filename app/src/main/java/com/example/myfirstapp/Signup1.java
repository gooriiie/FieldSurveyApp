package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Signup1 extends AppCompatActivity {

    LinearLayout signupLayout1;
    Button btn_nextSignup2;
    TextInputEditText editTextEmail;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        signupLayout1 = findViewById(R.id.signupLayout1);
        btn_nextSignup2 = findViewById(R.id.button_nextSignup2);
        editTextEmail = findViewById(R.id.editTextEmail);

        signupLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_nextSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString();
                Pattern emailPattern = Patterns.EMAIL_ADDRESS;
                if (!emailPattern.matcher(email).matches()) {
                    makeText(getApplicationContext(), "이메일형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    editTextEmail.requestFocus();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Signup2.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }

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