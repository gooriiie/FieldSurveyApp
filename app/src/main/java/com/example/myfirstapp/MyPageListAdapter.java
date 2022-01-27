package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPageListAdapter extends BaseAdapter {
    private List<listItem> myPageList = new ArrayList<>();

    public MyPageListAdapter() {
        myPageList.add(new listItem("비밀번호 변경", R.drawable.ic_baseline_settings_24));
        myPageList.add(new listItem("내 프로젝트", R.drawable.ic_baseline_list_alt_24));
    }

    @Override
    public int getCount() {
        return myPageList.size();
    }

    @Override
    public Object getItem(int i) {
        return myPageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context ctx = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mypage_list_item, viewGroup, false);
        }

        TextView tv_name = (TextView) view.findViewById(R.id.myPageListName);
        ImageView iv_rsc = (ImageView) view.findViewById(R.id.myPageListImg);

        listItem list = myPageList.get(i);

        tv_name.setText(list.getName());
        iv_rsc.setImageResource(list.getImageResource());

        return view;
    }
}
