package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    LinearLayout detailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        detailView = findViewById(R.id.detailView);

        Intent intent = getIntent();

        String address = intent.getStringExtra("address");

        TextView tv11 = findViewById(R.id.textView11);

        tv11.setText(address);

        db.collection("addresses").document(address)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String, Object> data = document.getData();
                                for (String r : data.keySet()) {
                                    if (r.equals("닉네임")) {
                                        continue;
                                    }
                                    HashMap<String, Integer> room = (HashMap<String, Integer>) data.get(r);

                                    TextView roomName = new TextView(getApplicationContext());
                                    roomName.setText("✔ " + r);
                                    roomName.setTextSize(20);
                                    roomName.setTypeface(null, Typeface.BOLD);

                                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    param.leftMargin = 70;
                                    param.topMargin = 40;

                                    roomName.setLayoutParams(param);

                                    detailView.addView(roomName);

                                    for (Object material : room.keySet()) {
                                        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        param2.leftMargin = 90;
                                        param2.topMargin = 30;

                                        TextView materialText = new TextView(getApplicationContext());
                                        materialText.setText(material + " : " + String.valueOf(room.get(material)) + "개");
                                        materialText.setTextSize(16);
                                        materialText.setLayoutParams(param2);
                                        detailView.addView(materialText);
                                    }
                                }
                            }
                        }
                    }
                });
    }
}