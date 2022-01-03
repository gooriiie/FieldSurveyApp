package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<String> addressList;

    ArrayAdapter<CharSequence> adspin1, adspin2, adspin3;
    String choice_city = "";
    String choice_region = "";
    String choice_dong = "";
    String detail_address = "";
    String choice_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addressList = new ArrayList<>();

        final Spinner spin1 = (Spinner) findViewById(R.id.city);
        final Spinner spin2 = (Spinner) findViewById(R.id.region);
        final Spinner spin3 = (Spinner) findViewById(R.id.dong);
        Button btn_next = (Button) findViewById(R.id.button_next);

        adspin1 = ArrayAdapter.createFromResource(this, R.array.spinner_city, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adspin1.getItem(i).equals("대전광역시")) {
                    choice_city = "대전광역시";

                    adspin2 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);

                    spin2.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            choice_region = adspin2.getItem(i).toString();

                            switch (adspin2.getItem(i).toString()) {
                                case "서구":
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon_seogu, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case "유성구":
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon_yuseong, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case "중구":
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon_junggu, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case "동구":
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon_donggu, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                case "대덕구":
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_daejeon_daedukgu, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                                default:
                                    adspin3 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_default, android.R.layout.simple_spinner_dropdown_item);
                                    break;
                            }
                            adspin3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(adspin3);

                            spin3.setOnItemSelectedListener(new OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    choice_dong = adspin3.getItem(i).toString();
                                    choice_address = choice_city + " " + choice_region + " " + choice_dong;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    makeText(getApplicationContext(), "동을 선택하세요", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            makeText(getApplicationContext(), "구를 선택하세요", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (adspin1.getItem(i).equals("세종특별자치시")) {
                    choice_city = "세종특별자치시";

                    adspin2 = ArrayAdapter.createFromResource(MainActivity2.this, R.array.spinner_city_default, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                makeText(getApplicationContext(), "시를 선택하세요", Toast.LENGTH_SHORT).show();
            }
        });


        // 다음으로 버튼 클릭시
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("addresses")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        addressList.add(document.getId());
                                    }
                                }
                                detail_address = ((EditText) findViewById(R.id.detailAddress)).getText().toString();
                                choice_address = choice_city + " " + choice_region + " " + choice_dong + " " + detail_address;

                                if (choice_city.equals("선택") || choice_city.equals("")) {
                                    makeText(getApplicationContext(), "시를 선택하세요", Toast.LENGTH_SHORT).show();
                                } else if (choice_region.equals("선택") || choice_region.equals("선택없음")) {
                                    makeText(getApplicationContext(), "구를 선택하세요", Toast.LENGTH_SHORT).show();
                                } else if (choice_dong.equals("선택") || choice_dong.equals("선택없음")) {
                                    makeText(getApplicationContext(), "동을 선택하세요", Toast.LENGTH_SHORT).show();
                                } else if (detail_address.equals("")) {
                                    makeText(getApplicationContext(), "상세주소를 입력하세요", Toast.LENGTH_SHORT).show();
                                } else if (addressList.contains(choice_address)) {
                                    makeText(getApplicationContext(), "이미 존재하는 주소입니다", Toast.LENGTH_SHORT).show();
                                } else {
                                    makeText(getApplicationContext(), choice_address, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                                    intent.putExtra("address", choice_address);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });


    }
}