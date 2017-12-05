package com.example.samuel.tddc73_lab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
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
    private final ArrayList<Pair<String, ArrayList<String>>> parentList;
    private final ArrayList<Pair<String, ArrayList<String>>> originalParentList;

    public ExpandableListAdapter(Context context, ArrayList<Pair<String, ArrayList<String>>> parentList) {
        System.out.println("constructor" + parentList);
        this.context = context;
        this.parentList = new ArrayList<>();
        this.parentList.addAll(parentList);
        this.originalParentList = new ArrayList<>();
        this.originalParentList.addAll(parentList);
        //searchData("falafel");

    }

    @Override
    public int getGroupCount() {
        Log.d("", "getGroupCount: return " + parentList.size());
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int parentPosition) {
        Log.d("", "getChildrenCount:" + parentPosition);
        return parentList.get(parentPosition).second.size();
    }

    @Override
    public String getGroup(int parentPosition) {
        Log.d("", "getGroup:" + parentPosition);
        return parentList.get(parentPosition).first;
    }

    @Override
    public String getChild(int parentPosition, int childPosition) {
        Log.d("", "getChild:" + parentPosition + childPosition);
        return parentList.get(parentPosition).second.get(childPosition);
    }
    @Override
    public long getGroupId(int parentPosition) {
        Log.d("", "getGroupId:" + parentPosition);
        return parentPosition;
    }

    @Override
    public long getChildId(int parentPosition , int childPosition) {
        Log.d("", "getChildId:" + parentPosition);
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        Log.d("", "hasStableIds: ");
        return false;
    }

    @Override
    public View getGroupView(int parentPosition, boolean isLastChild, View view, ViewGroup parent) {
        Log.d("", "getGroupView: " + parentPosition);

        if( view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_items, null);
        }

        TextView heading = (TextView) view;
        if (heading == null){
            System.out.println("its NULL!");
        }
        heading.setText(parentList.get(parentPosition).first);
        return view;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Log.d("", "getChildView: " + parentPosition + childPosition);
        String child = getChild(parentPosition, childPosition);

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_items, null);
        }
        TextView heading = (TextView) view;
        heading.setText(child);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        Log.d("", "isChildSelectable: ");
        return true;
    }

    public void searchData(String query){
        Log.d("", "searchData: " + query);
        query = query.toLowerCase();
        parentList.clear();

        if(query.isEmpty()){
            System.out.println("query is empty");
            parentList.addAll(originalParentList);
        }
        /*else {
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
                    parentList.add(currentValue.getKey(), newList);
                    iterator.remove(); // avoids a ConcurrentModificationException
                }
            }
        }*/
        System.out.println("parent list after search" + parentList);
        notifyDataSetChanged();
    }
}
