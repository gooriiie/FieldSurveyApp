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
         * ???????????? ???????????? ??????
         * ??????????????? ???????????? ?????? ????????? ???????????? ?????? ???????????????
         */
        btn_certificationRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidPhoneNumber(editTextPhoneNumber.getText().toString())) {
                    phoneNumber = editTextPhoneNumber.getText().toString().trim();
                    // ???????????? ?????? ?????? ??????

                } else {
                    makeText(getApplicationContext(), "??????????????? ????????? ?????? ????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * ????????????
         * ?????? ??????????????? ??????????????? ?????? ????????? ???????????????
         * ?????? ????????? ???????????? ???????????? ??????????????? ???????????? ??? ????????? ????????????
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
                                    user.put("??????", name);
                                    user.put("?????????", email);
                                    user.put("????????????", password);
                                    user.put("???????????????", phoneNumber);

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
                                                    makeText(getApplicationContext(), "???????????? ??????????????? ??????????????????.", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    makeText(getApplicationContext(), "??????????????? ??????????????????", Toast.LENGTH_SHORT).show();
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