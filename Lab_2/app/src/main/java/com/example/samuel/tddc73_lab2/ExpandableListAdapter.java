package com.example.samuel.tddc73_lab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by samuel on 11/26/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final HashMap<String, ArrayList<String>> parentList;
    private final HashMap<String, ArrayList<String>> originalParentList;

    public ExpandableListAdapter(Context context, HashMap<String, ArrayList<String>> parentList) {
        //System.out.println("constructor" + parentList);
        this.context = context;
        this.parentList = new HashMap<>();
        this.parentList.putAll(parentList);
        this.originalParentList = new HashMap<>();
        this.originalParentList.putAll(parentList);
        searchData("a");

    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int parentPosition) {
        return parentList.get(parentPosition).size();
    }

    @Override
    public Object getGroup(int parentPosition) {
        System.out.println("getGroup, parentPosition " + parentPosition);
        System.out.println("parentList "+ parentList);
        return parentList.get(parentPosition);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int parentPosition) {
        return parentPosition;
    }

    @Override
    public long getChildId(int parentPosition , int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parentPosition, boolean isLastChild, View view, ViewGroup parent) {
        System.out.println("GET group view " + parentPosition);
        ArrayList childList = (ArrayList) getGroup(parentPosition);
        if( view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_main, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText("Papa poop");
        return view;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String child = (String) getChild(parentPosition, childPosition);
        System.out.println("GET child view "+ child);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_main, null);
        }
        TextView heading = (TextView) view.findViewById(R.id.child);
        heading.setText("Child poop");

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void searchData(String query){
        query = query.toLowerCase();
        parentList.clear();

        if(query.isEmpty()){
            System.out.println("query is empty");
            parentList.putAll(originalParentList);
        }
        else {
            Iterator<Map.Entry<String, ArrayList<String>>> iterator = originalParentList.entrySet().iterator();
            System.out.println();
            while (iterator.hasNext()) {
                ArrayList<String> newList = new ArrayList<>();
                Map.Entry<String, ArrayList<String>> currentValue = iterator.next();
                for (String child : currentValue.getValue()) {
                    if (child.toLowerCase().contains(query) ||
                            child.toLowerCase().contains(query)) {
                        System.out.println("FOUND IT BEAATCH!" + child);
                        newList.add(child);
                    }
                }
                if (newList.size() > 0) {
                    System.out.println("SIZE" + newList.size());
                    parentList.put(currentValue.getKey(), newList);
                    iterator.remove(); // avoids a ConcurrentModificationException
                }
            }
        }
        System.out.println("parent list after search" + parentList);
        notifyDataSetChanged();
    }
}
