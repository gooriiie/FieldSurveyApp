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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ChangePassword2 extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;
    LinearLayout changePasswordLayout2;
    Button btn_completeChangePassword;
    TextInputEditText editTextNewPassword;
    String newPassword, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password2);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        changePasswordLayout2 = findViewById(R.id.changePasswordLayout2);
        btn_completeChangePassword = findViewById(R.id.button_completeChangePassword);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);

        changePasswordLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        btn_completeChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = auth.getCurrentUser();
                newPassword = editTextNewPassword.getText().toString();

                user.updatePassword(newPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Map<String, String> updatePassword = new HashMap<>();
                                    updatePassword.put("비밀번호", newPassword);
                                    db.collection("users").document(user.getUid())
                                            .set(updatePassword, SetOptions.merge());

                                    makeText(getApplicationContext(), "비밀번호 변경 완료", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    makeText(getApplicationContext(), "비밀번호 변경 실패", Toast.LENGTH_SHORT).show();
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