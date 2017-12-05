package com.example.samuel.tddc73_lab2;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{


    private ArrayList<Pair<String, ArrayList<String>>> newData = new ArrayList<>();
    private com.example.samuel.tddc73_lab2.ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();
        displayList();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        return false;
    }

    public void populateData(){

        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.add("Dragon");
        vehicles.add("Falafel");

        ArrayList<String> food = new ArrayList<>();
        food.add("Humus");
        food.add("Falafel");

        Pair<String, ArrayList<String>> vehiclePair = new Pair<>("Vehicles", vehicles);
        Pair<String, ArrayList<String>> foodPair = new Pair<>("Food", food);
        newData.add(vehiclePair);
        newData.add(foodPair);


    }
    private void displayList() {
        //get reference to the ExpandableListView
        ExpandableListView listView = findViewById(R.id.ListView);
        //create the adapter by passing ArrayList data
        expandableListAdapter = new com.example.samuel.tddc73_lab2.ExpandableListAdapter(MainActivity.this, newData);
        //attach the adapter to the list
        listView.setAdapter(expandableListAdapter);

    }
}
