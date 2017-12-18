package com.example.samuel.tddc73_lab2;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by samuel on 11/26/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final ArrayList<Pair<String, ArrayList<String>>> parentList;
    private final ArrayList<Pair<String, ArrayList<String>>> originalParentList;

    public ExpandableListAdapter(Context context, ArrayList<Pair<String, ArrayList<String>>> parentList) {
        this.context = context;
        this.parentList = new ArrayList<>();
        this.parentList.addAll(parentList);
        this.originalParentList = new ArrayList<>();
        this.originalParentList.addAll(parentList);

    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int parentPosition) {
        return parentList.get(parentPosition).second.size();
    }

    @Override
    public String getGroup(int parentPosition) {
        return parentList.get(parentPosition).first;
    }

    @Override
    public String getChild(int parentPosition, int childPosition) {
        return parentList.get(parentPosition).second.get(childPosition);
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

        if( view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.parent_items, null);
        }

        TextView heading = (TextView) view;
        heading.setText(parentList.get(parentPosition).first);
        view.setTag(parentList.get(parentPosition).first);
        return view;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String child = getChild(parentPosition, childPosition);

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_items, null);
        }
        TextView heading = (TextView) view;
        heading.setText(child);
        view.setBackgroundColor(Color.WHITE);
        //Set unique tag for every childView
        Integer childString = childPosition;
        Integer parentString = parentPosition;
        view.setTag(parentString.toString() + childString.toString());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
