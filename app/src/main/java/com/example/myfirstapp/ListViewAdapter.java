package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Space;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<SpaceItem> itemList = new ArrayList<>();

    public ListViewAdapter() {

    }

    public void addItem(List<String> list) {
        for (String l : list) {
            SpaceItem item = new SpaceItem(l);
            itemList.add(item);
        }
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
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
            view = inflater.inflate(R.layout.space_item, viewGroup, false);
        }

        TextView tv_name = (TextView) view.findViewById(R.id.spaceName);

        SpaceItem spaceItem = itemList.get(i);

        tv_name.setText(spaceItem.getName());



        return view;
    }
}
