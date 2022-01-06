package com.example.myfirstapp;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectSpace extends AppCompatActivity {
    ListView listView;
    String getAddress;
    String getNickName;
    ListViewAdapter adapter;

    List<String> spaces = Arrays.asList("거실", "주방", "침실1", "침실2", "침실3", "욕실1", "욕실2", "펜트리", "실외기실");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_space);

        Intent intent = getIntent();
        getAddress = intent.getStringExtra("address");
        getNickName = intent.getStringExtra("nickName");

        TextView address = (TextView) findViewById(R.id.address2);
        address.setText(getAddress);

        listView = findViewById(R.id.listView1);
        adapter = new ListViewAdapter();
        adapter.addItem(spaces);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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