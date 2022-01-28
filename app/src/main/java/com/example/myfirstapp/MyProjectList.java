package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyProjectList extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    SwipeRefreshLayout swipeRefreshLayout2;
    RecyclerView recyclerView2;
    LinearLayoutManager mLayoutManager;
    ItemAdapter adapter;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project_list);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        Toolbar toolbar_my_project = findViewById(R.id.toolbar_my_project);

        setSupportActionBar(toolbar_my_project);
        getSupportActionBar().setTitle(R.string.my_project_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout2 = findViewById(R.id.swipeRefreshLayout2);
        recyclerView2 = findViewById(R.id.recyclerView2);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(mLayoutManager);
        itemList = new ArrayList<Item>();
        adapter = new ItemAdapter(MyProjectList.this, itemList);

        db.collection("addresses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String eachNickName = "";
                                String writer = "";

                                Map<String, Object> data = document.getData();
                                for (String r : data.keySet()) {
                                    if (r.equals("닉네임")) {
                                        eachNickName = (String) data.get(r);
                                    } else if (r.equals("작성자")) {
                                        writer = (String) data.get(r);
                                    }
                                }
                                if (writer.equals(auth.getCurrentUser().getUid())) {
                                    Item rc = new Item(eachNickName, document.getId());
                                    itemList.add(rc);
                                    recyclerView2.setAdapter(adapter);
                                }
                            }
                        }
                    }
                });

        swipeRefreshLayout2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemList.clear();
                db.collection("addresses")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String eachNickName = "";
                                        String writer = "";

                                        Map<String, Object> data = document.getData();
                                        for (String r : data.keySet()) {
                                            if (r.equals("닉네임")) {
                                                eachNickName = (String) data.get(r);
                                            } else if (r.equals("작성자")) {
                                                writer = (String) data.get(r);
                                            }
                                        }
                                        if (writer.equals(auth.getCurrentUser().getUid())) {
                                            Item rc = new Item(eachNickName, document.getId());
                                            itemList.add(rc);
                                            adapter.notifyDataSetChanged();
                                            recyclerView2.setAdapter(adapter);
                                            swipeRefreshLayout2.setRefreshing(false);
                                        }
                                    }
                                }
                            }
                        });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}