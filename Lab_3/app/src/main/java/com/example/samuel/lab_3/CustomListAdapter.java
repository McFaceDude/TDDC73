package com.example.samuel.lab_3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by samuel on 12/17/17.
 */

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<String> array;
    private Context context;
    public CustomListAdapter(ArrayList<String> array, Context ct){
        this.context = ct;

        this.array = array;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CustomView customView;
        if (view == null){

            customView = new CustomView(context, array.get(i));
            System.out.println("get view: " + customView.getClass().getName());
        }
        else{
            System.out.println("get view, not null");
            return view;
        }
        return customView;
    }
}
