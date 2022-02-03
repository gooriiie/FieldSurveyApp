package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup3 extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    LinearLayout signupLayout3;
    Button btn_certification, btn_certificationRequest;
    TextInputEditText editTextPhoneNumber, editTextCertificationNumber;
    String email, password, phoneNumber, name, certificationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        email = intent.getStringExtra("email").toString();
        password = intent.getStringExtra("password").toString();
        name = intent.getStringExtra("name").toString().trim();

        signupLayout3 = findViewById(R.id.signupLayout3);
        btn_certification = findViewById(R.id.button_certification);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextCertificationNumber = findViewById(R.id.editTextCertificationNumber);
        btn_certificationRequest = findViewById(R.id.button_certificationRequest);

        signupLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        /**
         * 전화번호 인증번호 전송
         * 전화번호를 입력하면 해당 번호로 인증번호 전송 구현해야함
         */
        btn_certificationRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidPhoneNumber(editTextPhoneNumber.getText().toString())) {
                    phoneNumber = editTextPhoneNumber.getText().toString().trim();
                    // 인증번호 전송 과정 필요

                } else {
                    makeText(getApplicationContext(), "휴대폰번호 형식에 맞지 않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 인증완료
         * 받은 인증번호가 일치하는지 확인 하는것 구현해야함
         * 인증 번호가 일치하면 이메일과 비밀번호로 회원가입 후 디비에 회원저장
         */
       btn_certification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup3.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String uid = auth.getCurrentUser().getUid();
                                    Map<String, String> user = new HashMap<>();
                                    user.put("이름", name);
                                    user.put("이메일", email);
                                    user.put("비밀번호", password);
                                    user.put("휴대폰번호", phoneNumber);

                                    db.collection("users").document(uid)
                                            .set(user, SetOptions.merge())
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Intent intent = new Intent(getApplicationContext(), Signup5.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    makeText(getApplicationContext(), "사용자를 추가하는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    makeText(getApplicationContext(), "회원가입에 실패했습니다", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "\\d{11}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (this.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}