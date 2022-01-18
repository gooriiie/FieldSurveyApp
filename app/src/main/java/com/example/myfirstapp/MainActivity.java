package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 전체 Activity 순서
 * 현장 추가 : main -> main2 -> select_space -> main4
 * 현장 조회 : main -> main7 -> main5 -> select_space
 * 회원가입 : signup1 -> signup2 -> signup4 -> signup3 -> signup5
 */
public class MainActivity extends AppCompatActivity  {

    SpaceListFragment spaceListFragment;
    MyPageFragment myPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceListFragment = new SpaceListFragment();
        myPageFragment = new MyPageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, spaceListFragment).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);

        bottom_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.firstTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, spaceListFragment).commit();
                        return true;
                    case R.id.secondTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myPageFragment).commit();
                        return true;
                }
                return false;
            }
        });
        //
//        auth = FirebaseAuth.getInstance();
//
//        makeText(getApplicationContext(), auth.getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();
//
//        recyclerView = findViewById(R.id.recyclerView);
//        mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(mLayoutManager);
//        itemList = new ArrayList<Item>();
//
//        EditText searchBar = findViewById(R.id.searchBar);
//
//        searchBar.addTextChangedListener(this);
//
//        adapter = new ItemAdapter(this, itemList);
//
//        // FireStore에서 주소 불러오기
//        db.collection("addresses")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                String eachNickName = "";
//
//                                Map<String, Object> data = document.getData();
//                                for (String r : data.keySet()) {
//                                    if (r.equals("닉네임")) {
//                                        eachNickName = (String) data.get(r);
//                                        break;
//                                    }
//                                }
//                                Item rc = new Item(eachNickName, document.getId());
//                                itemList.add(rc);
//                                recyclerView.setAdapter(adapter);
//                            }
//                        }
//                    }
//                });
//
//        Button button_addspace = findViewById(R.id.button_addspace);
//        button_addspace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//                startActivity(intent);
//            }
//        });
    }

//    @Override
//    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        adapter.getFilter().filter(charSequence.toString());
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//
//    }
}