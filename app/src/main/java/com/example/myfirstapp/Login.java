package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    String email, password;
    Button btn_login;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
        email = auto.getString("email", null);
        password = auto.getString("password", null);

        // 수정할부분 SharedPreferences clear도 구현해야함
        if ((email != null) && (password != null)) {
            login(email, password);
        } else {
            editTextEmail = findViewById(R.id.loginEmail);
            editTextPassword = findViewById(R.id.loginPassword);
            btn_login = findViewById(R.id.button_login);

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    email = editTextEmail.getText().toString().trim();
                    password = editTextPassword.getText().toString().trim();

//                db 확인 후 id, pw 일치하면 login 성공
                    login(email, password);
                }
            });
        }

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = auth.getCurrentUser();
                if (currentUser != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }

    void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                            SharedPreferences auto = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                            SharedPreferences.Editor autoLoginEdit = auto.edit();
                            autoLoginEdit.putString("email", email);
                            autoLoginEdit.putString("password", password);
                            autoLoginEdit.commit();
                            My_Shared_Preference.setUserEmailPassword(getApplicationContext(), email, password);

                            auth.addAuthStateListener(authStateListener);
                        } else {
                            makeText(getApplicationContext(), "이메일 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            auth.removeAuthStateListener(authStateListener);
        }
    }
}