package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpaceListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpaceListFragment extends Fragment implements TextWatcher {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    private Context ctx;

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    ItemAdapter adapter;
    List<Item> itemList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpaceListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment spacelist.
     */
    // TODO: Rename and change types and number of parameters
    public static SpaceListFragment newInstance(String param1, String param2) {
        SpaceListFragment fragment = new SpaceListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup mainView = (ViewGroup) inflater.inflate(R.layout.fragment_spacelist, container, false);

        ctx = container.getContext();
        auth = FirebaseAuth.getInstance();

        swipeRefreshLayout = mainView.findViewById(R.id.swipeRefreshLayout);
        recyclerView = mainView.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        itemList = new ArrayList<Item>();

        EditText searchBar = mainView.findViewById(R.id.searchBar);

        searchBar.addTextChangedListener(this);

        adapter = new ItemAdapter(ctx, itemList);

        List<Item> tmp = new ArrayList<>();

        // FireStore에서 주소 불러오기
        db.collection("addresses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String eachNickName = "";

                                Map<String, Object> data = document.getData();
                                for (String r : data.keySet()) {
                                    if (r.equals("닉네임")) {
                                        eachNickName = (String) data.get(r);
                                        break;
                                    }
                                }
                                Item rc = new Item(eachNickName, document.getId());
                                itemList.add(rc);
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    }
                });

        Button button_addspace = mainView.findViewById(R.id.button_addspace);
        button_addspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MainActivity2.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                                        String date = "";

                                        Map<String, Object> data = document.getData();
                                        for (String r : data.keySet()) {
                                            if (r.equals("닉네임")) {
                                                eachNickName = (String) data.get(r);
                                                break;
                                            }
                                        }
                                        Item rc = new Item(eachNickName, document.getId());
                                        itemList.add(rc);
                                        adapter.notifyDataSetChanged();
                                        recyclerView.setAdapter(adapter);

                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                }
                            }
                        });

            }
        });

        // Inflate the layout for this fragment
        return mainView;
    }

    //
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.getFilter().filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}