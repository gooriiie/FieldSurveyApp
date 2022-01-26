package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity7 extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    LinearLayout sumView;
    Map<String, Integer> resultSum;
    String writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Toolbar toolbar_main7 = findViewById(R.id.toolbar_main7);

        setSupportActionBar(toolbar_main7);
        getSupportActionBar().setTitle(R.string.menu_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resultSum = new HashMap<>();

        sumView = findViewById(R.id.sumView);

        Intent intent = getIntent();

        String address = intent.getStringExtra("address");
        String nickName = intent.getStringExtra("nickName");

        TextView tv13 = findViewById(R.id.textView13);
        TextView tv14 = findViewById(R.id.textView14);
        TextView tv15 = findViewById(R.id.textView15);

        tv13.setText(address);

        Button btn_checkDetail = findViewById(R.id.button_checkDetail);

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
                                    } else if (r.equals("작성자")) {
                                        writer = (String) (data.get(r));
                                        db.collection("users").document(writer)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot document = task.getResult();
                                                            if (document.exists()) {
                                                                Map<String, Object> data = document.getData();
                                                                for (String s : data.keySet()) {
                                                                    if (s.equals("이름")) {
                                                                        tv14.setText((String) (data.get(s)));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                    } else if (r.equals("작성시간")) {
                                        tv15.setText((String) (data.get(r)));
                                    } else {
                                        HashMap<String, Integer> room = (HashMap<String, Integer>) data.get(r);

//                                    TextView roomName = new TextView(getApplicationContext());
//                                    roomName.setText("✔ " + r);
//                                    roomName.setTextSize(20);
//                                    roomName.setTypeface(null, Typeface.BOLD);
//
//                                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
//                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                                    param.leftMargin = 70;
//                                    param.topMargin = 40;

//                                    roomName.setLayoutParams(param);

//                                    detailView.addView(roomName);

                                        for (Object material : room.keySet()) {
                                            if (resultSum.containsKey(material)) {
                                                resultSum.put((String) material, resultSum.get(material) + Integer.parseInt(String.valueOf(room.get(material))));
                                            } else {
                                                resultSum.put((String) material, Integer.parseInt(String.valueOf(room.get(material))));
                                            }
                                        }
                                    }


                                }
                                LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                param2.leftMargin = 90;
                                param2.topMargin = 30;

                                for (String m : resultSum.keySet()) {
                                    TextView materialText = new TextView(getApplicationContext());
                                    materialText.setText("●  " + m + " : " + String.valueOf(resultSum.get(m)) + "개");
                                    materialText.setTextSize(16);
                                    materialText.setLayoutParams(param2);
                                    sumView.addView(materialText);
                                }
                            }
                        }
                    }
                });

        btn_checkDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                intent.putExtra("address", address);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_call:
                db.collection("users").document(writer)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Map<String, Object> data = document.getData();
                                        for (String s : data.keySet()) {
                                            if (s.equals("휴대폰번호")) {
                                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + (String) (data.get(s))));
                                                startActivity(intent);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        });
                break;
            case R.id.action_delete:
                makeText(getApplicationContext(), "delete button", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}