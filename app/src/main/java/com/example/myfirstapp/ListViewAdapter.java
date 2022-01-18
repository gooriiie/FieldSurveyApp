package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<SpaceItem> itemList = new ArrayList<>();
    private List<String> visibleItemList = new ArrayList<>();
    private List<String> invisibleItemList = new ArrayList<>();


    public ListViewAdapter() {

    }

    public void addItem(List<String> list) {
        for (String l : list) {
            SpaceItem item = new SpaceItem(l);
            itemList.add(item);
        }
    }

    public void addVisibleItem(List<String> list) {
        for (String l : list) {
            visibleItemList.add(l);
        }
    }

    public void addInvisibleItem(List<String> list) {
        for (String l : list) {
            invisibleItemList.add(l);
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
        ImageView img = (ImageView) view.findViewById(R.id.saveCheck);

        SpaceItem spaceItem = itemList.get(i);

//        if (visibleItemList.contains(spaceItem.getName())) {
//            img.setVisibility(View.VISIBLE);
//        } else {
//            img.setVisibility(View.INVISIBLE);
//        }

        boolean ck = false;

        for (String vi : visibleItemList) {
            if (vi.contains(spaceItem.getName())) {
                img.setVisibility(View.VISIBLE);
                ck = true;
                break;
            }
        }
        if (!ck) {
            img.setVisibility(View.INVISIBLE);
        }

        tv_name.setText(spaceItem.getName());

        return view;
    }
}
