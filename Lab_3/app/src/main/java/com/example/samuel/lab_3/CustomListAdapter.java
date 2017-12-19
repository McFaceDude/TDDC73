package com.example.samuel.lab_3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by samuel on 12/17/17.
 */

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<String> array;
    private Context context;
    CustomListAdapter(ArrayList<String> array, Context ct){
        this.context = ct;

        this.array = array;
    }
    void addItem(String item){
        array.add(item.toLowerCase());
    }
    void clearItems(){
        array.clear();
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public String getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CustomView customView;
        if (view == null){
            customView = new CustomView(context);
        }
        else{
            customView = (CustomView)view;
        }

        customView.setName(array.get(i));
        return customView;
    }
}
