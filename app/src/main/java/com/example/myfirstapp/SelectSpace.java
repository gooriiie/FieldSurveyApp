package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectSpace extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView ListView;
    String getAddress;
    String getNickName;
    ListViewAdapter adapter;

    List<String> spaces = Arrays.asList("거실", "주방", "침실1", "침실2", "침실3", "욕실1", "욕실2", "펜트리", "실외기실");
    List<String> visibleSpace, invisibleSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_space);

        visibleSpace = new ArrayList<>();
        invisibleSpace = new ArrayList<>();

        Intent intent = getIntent();
        getAddress = intent.getStringExtra("address");
        getNickName = intent.getStringExtra("nickName");

        TextView address = (TextView) findViewById(R.id.address2);
        address.setText(getAddress);

        ListView = findViewById(R.id.listView1);
        adapter = new ListViewAdapter();
        adapter.addItem(spaces);

        db.collection("addresses").document(getAddress)
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
                                    if (spaces.contains(r)) {
                                        visibleSpace.add(r);
                                    } else {
                                        invisibleSpace.add(r);
                                    }

                                }
                            }
                            adapter.addVisibleItem(visibleSpace);
                            adapter.addInvisibleItem(invisibleSpace);
                            ListView.setAdapter(adapter);
                        }
                    }
                });

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpaceItem item = (SpaceItem) adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                intent.putExtra("address", getAddress);
                intent.putExtra("nickName", getNickName);
                intent.putExtra("room", item.getName());

                startActivity(intent);
            }
        });

    }


}