package com.example.samuel.tddc73_lab2;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener{


    private ArrayList<Pair<String, ArrayList<String>>> data = new ArrayList<>();
    private com.example.samuel.tddc73_lab2.ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText search =  findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("before text");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("on text");
                search(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println("After text change");

            }
        });

        populateData();
        displayList();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        Log.d("", "onChildClick: clicked");
        return false;
    }

    public void search(String query){
        query = query.toLowerCase();
        List splitedString;
        boolean parentMatch = false;

        if (query.contains("/")){
            splitedString = Arrays.asList(query.split("/"));
            System.out.println("split result:" + splitedString.get(1));
            for (Pair<String, ArrayList<String>> parent: data){
                if (parent.first.toLowerCase().contains(splitedString.get(1).toString())) {

                    if (parent.first.toLowerCase().equals(splitedString.get(1).toString())){
                        System.out.println("Parent match on: " + splitedString.get(1).toString());

                        parentMatch = true;
                        if(splitedString.size() > 2){
                            Integer childMatch =  childMatch(parent.second, splitedString.get(2).toString().toLowerCase());
                            if (childMatch != null){
                                if (childMatch == -1){
                                    System.out.println("partial match o child");
                                }
                                else{
                                    System.out.println("child match on child:" + splitedString.get(childMatch));
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("partial match on parent: " + parent.first.toLowerCase());
                    }
                }
            }

        }
        else{

            System.out.println("no / sign");
        }

    }

    public Integer childMatch(List children, String query){
        Log.d("", "childMatch: childlist" + children);
        Integer index = 0;
        for(Object child: children){
            if (child.toString().toLowerCase().contains(query)){
                if(child.toString().toLowerCase().equals(query)){
                    return index;
                }
                else{
                    return -1;
                }
            }
            index += 1;
        }
        return null;
    }

    public void populateData(){

        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.add("Dragon");
        vehicles.add("Boat");
        vehicles.add("Balloon");

        ArrayList<String> life = new ArrayList<>();
        life.add("Falafel");
        life.add("Dragon");
        life.add("Dancing");
        life.add("Disco");

        ArrayList<String> food = new ArrayList<>();
        food.add("Humus");
        food.add("Falafel");
        food.add("Falafel");

        Pair<String, ArrayList<String>> vehiclePair = new Pair<>("Vehicles", vehicles);
        Pair<String, ArrayList<String>> foodPair = new Pair<>("Food", food);
        Pair<String, ArrayList<String>> lifePair = new Pair<>("Life", life);
        data.add(vehiclePair);
        data.add(foodPair);
        data.add(lifePair);
    }
    private void displayList() {
        //get reference to the ExpandableListView
        ExpandableListView listView = findViewById(R.id.ListView);
        //create the adapter by passing ArrayList data
        expandableListAdapter = new com.example.samuel.tddc73_lab2.ExpandableListAdapter(MainActivity.this, data);
        //attach the adapter to the list
        listView.setAdapter(expandableListAdapter);

    }
}
