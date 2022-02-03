package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * 전체 Activity 순서
 * 현장 추가 : main -> main2 -> select_space -> main4
 * 현장 조회 : main -> main7 -> main5 -> select_space
 * 회원가입 : signup1 -> signup2 -> signup4 -> signup3 -> signup5
 */

public class MainActivity extends AppCompatActivity {

    SpaceListFragment spaceListFragment;
    MyPageFragment myPageFragment;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
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

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
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