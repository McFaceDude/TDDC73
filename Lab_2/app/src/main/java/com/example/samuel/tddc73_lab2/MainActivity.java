package com.example.samuel.tddc73_lab2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{


    private ArrayList<Pair<String, ArrayList<String>>> data = new ArrayList<>();
    private ArrayList<String> parentList = new ArrayList<>();
    private com.example.samuel.tddc73_lab2.ExpandableListAdapter expandableListAdapter;
    ExpandableListView listView;
    EditText searchField;
    boolean parentExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.ListView);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int parentId, long l) {
                if (listView.isGroupExpanded(parentId)){
                    listView.collapseGroup(parentId);
                }
                else{
                    parentExpanded = true;
                    listView.expandGroup(parentId);
                    searchField.setText("/" + parentList.get(parentId));
                    searchField.setSelection(parentList.get(parentId).length() + 1);
                    collapseAllExcept(parentId);
                }
                return true;
            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int parentId, int childId, long l) {

                resetBackgroundColors(parentId);
                int index = parent.getFlatListPosition(parent.getPackedPositionForChild(parentId, childId));
                parent.getChildAt(index).setBackgroundColor(Color.LTGRAY);

                String text = "/" + parentList.get(parentId) +"/" +  data.get(parentId).second.get(childId);
                searchField.setText(text);
                searchField.setSelection(text.length());
                return true;
            }
        });
        searchField =  findViewById(R.id.search);
        searchField.setSelection(1);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    searchField.setText("/");

                }
                else if(charSequence.toString().equals("/")) {
                    searchField.setBackgroundColor(Color.WHITE);
                    searchField.setSelection(1);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(searchField.length() > 1){
                    search(editable.toString());
                }
            }
        });

        populateData();
        displayList();
    }

    public void resetBackgroundColors(Integer parentId){
        int childCount = expandableListAdapter.getChildrenCount(parentId);
        for(Integer i = 0; i <childCount; i++){
            listView.findViewWithTag(parentId.toString() + i.toString()).setBackgroundColor(Color.WHITE);
        }
    }

    public void collapseAllExcept(int exceptionIndex){
        for(int i = 0; i < parentList.size(); i++){
            if(listView.isGroupExpanded(i) && i != exceptionIndex){
                listView.collapseGroup(i);
           }
        }
    }
    @Override
    public boolean onChildClick(ExpandableListView parent, View view, int i, int i1, long l) {
        return true;
    }

    public void search(String query){
        searchField.setBackgroundColor(Color.WHITE);
        List splitedString;
        splitedString = Arrays.asList(query.split("/", 3));
        ArrayList<String> potentialParents = potentialMatches(parentList, splitedString.get(1).toString());

        if (potentialParents.size() > 0){
            Integer parentMatchIndex =  match(parentList, splitedString.get(1).toString());
            //Parent match
            if (parentMatchIndex != null){
                if(!listView.isGroupExpanded(parentMatchIndex)){

                    listView.expandGroup(parentMatchIndex);
                    collapseAllExcept(parentMatchIndex);
                }
                //Child searched for
                if (splitedString.size() > 2){
                    ArrayList<String> potentialChildren = potentialMatches(data.get(parentMatchIndex).second, splitedString.get(2).toString());

                    //Potential children found
                    if(potentialChildren.size() > 0) {
                        Integer childMatchIndex = match(data.get(parentMatchIndex).second, splitedString.get(2).toString());

                        //Child match
                        if (potentialChildren.size() > 0 && childMatchIndex != null) {
                            resetBackgroundColors(parentMatchIndex);
                            listView.findViewWithTag(parentMatchIndex.toString() + childMatchIndex.toString()).setBackgroundColor(Color.LTGRAY);
                        }
                    }
                    //No child match
                    else{
                        resetBackgroundColors(parentMatchIndex);
                        searchField.setBackgroundColor(Color.RED);
                    }
                }
            }
            if (parentMatchIndex == null){
                //Collapse all groups
                collapseAllExcept(-1);
            }
        }
        else{
            searchField.setBackgroundColor(Color.RED);
            //Collapse all groups
            collapseAllExcept(-1);
        }
    }

    public ArrayList<String> potentialMatches(ArrayList<String> pMatches, String query){
        ArrayList<String> potentialMatchesIndex = new ArrayList<>();
        for(int i=0; i <pMatches.size(); i++){
            if (pMatches.get(i).length() >= query.length() && pMatches.get(i).substring(0, query.length()).equals(query)){
                potentialMatchesIndex.add(pMatches.get(i));
            }
        }
        return potentialMatchesIndex;
    }

    public Integer match(ArrayList<String> potentialMatches, String query){
        for(int i=0; i <potentialMatches.size(); i++){
            if (potentialMatches.get(i).equals(query)){
                return i;
            }
        }
        return null;
    }

    public void populateData(){

        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.add("Dragon");
        vehicles.add("Boat");
        vehicles.add("Balloon");
        vehicles.add("Whale");

        ArrayList<String> life = new ArrayList<>();
        life.add("Falafel");
        life.add("Dragon");
        life.add("Dancing");
        life.add("Light");

        ArrayList<String> food = new ArrayList<>();
        food.add("Hummus");
        food.add("Bread");
        food.add("Falafel");
        food.add("Falafel");

        Pair<String, ArrayList<String>> vehiclePair = new Pair<>("Vehicles", vehicles);
        Pair<String, ArrayList<String>> foodPair = new Pair<>("Food", food);
        Pair<String, ArrayList<String>> lifePair = new Pair<>("Life", life);
        parentList.add("Vehicles");
        parentList.add("Food");
        parentList.add("Life");

        data.add(vehiclePair);
        data.add(foodPair);
        data.add(lifePair);
    }
    private void displayList() {
        ExpandableListView listView = findViewById(R.id.ListView);
        expandableListAdapter = new com.example.samuel.tddc73_lab2.ExpandableListAdapter(MainActivity.this, data);
        listView.setAdapter(expandableListAdapter);
    }
}
