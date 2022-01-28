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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class ChangePassword extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    LinearLayout changePasswordLayout1;
    Button btn_nextChangePassword;
    TextInputEditText editTextCurrentPassword;
    String currentPassword, inputCurrentPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        changePasswordLayout1 = findViewById(R.id.changePasswordLayout1);
        btn_nextChangePassword = findViewById(R.id.button_nextChangePassword);
        editTextCurrentPassword = findViewById(R.id.editTextCurrentPassword);

        changePasswordLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_nextChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputCurrentPassword = editTextCurrentPassword.getText().toString();

                db.collection("users").document(auth.getCurrentUser().getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Map<String, Object> data = document.getData();
                                        for (String r : data.keySet()) {
                                            if (r.equals("비밀번호")) {
                                                currentPassword = (String) (data.get(r));
                                                break;
                                            }
                                        }
                                        if (currentPassword.equals(inputCurrentPassword)) {
                                            Intent intent = new Intent(getApplicationContext(), ChangePassword2.class);
                                            startActivity(intent);
                                        } else {
                                            makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                            }
                        });
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