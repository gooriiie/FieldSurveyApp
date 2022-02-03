package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    LinearLayout detailView;
    String writer, address, nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        auth = FirebaseAuth.getInstance();

        Toolbar toolbar_main5 = findViewById(R.id.toolbar_main5);

        setSupportActionBar(toolbar_main5);
        getSupportActionBar().setTitle(R.string.menu_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailView = findViewById(R.id.detailView);

        Intent intent = getIntent();

        address = intent.getStringExtra("address");
        nickName = intent.getStringExtra("nickName");

        TextView tv11 = findViewById(R.id.textView11);
        TextView tv16 = findViewById(R.id.textView16);
        TextView tv17 = findViewById(R.id.textView17);

        tv11.setText(address);

        Button btn_correct = findViewById(R.id.button_correct);

        /**
         * 디비에서 프로젝트 상세정보 조회
         * 클라우드로 변경시 수정필요
         */
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
                                                                        tv16.setText((String) (data.get(s)));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                    } else if (r.equals("작성시간")) {
                                        tv17.setText((String) (data.get(r)));
                                    } else {
                                        HashMap<String, Integer> room = (HashMap<String, Integer>) data.get(r);

                                        TextView roomName = new TextView(getApplicationContext());
                                        roomName.setText("✔ " + r);
                                        roomName.setTextColor(Color.BLACK);
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
                    }
                });

        btn_correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectSpace.class);
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
            /**
             * 전화 연결 기능(기능 사용하지 않을수도 있음)
             * 클라우드로 변경시 수정필요
             */
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
            /**
             * 디비에서 프로젝트 삭제
             * 클라우드로 변경시 수정필요
             */
            case R.id.action_delete:
                if (writer.equals(auth.getCurrentUser().getUid())) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5.this);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setTitle("경고!");
                    builder.setMessage("프로젝트를 삭제하시겠습니까?");
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.collection("addresses").document(address)
                                    .delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            makeText(getApplicationContext(), "삭제를 성공했습니다", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            makeText(getApplicationContext(), "삭제를 실패했습니다", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                } else {
                    makeText(getApplicationContext(), "삭제 권한이 없습니다", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}