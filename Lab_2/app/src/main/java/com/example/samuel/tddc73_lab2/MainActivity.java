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
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {


                for(int pIndex=0; pIndex < parentList.size(); pIndex++){
                    System.out.println("pindex: "+ pIndex + " i: " + i);
                    if(pIndex == i){

                        Log.d("", "onGroupClick: if" );
                        if (listView.isGroupExpanded(i)) {
                            listView.collapseGroup(i);
                            Log.d("", "onGroupClick: if if");
                            parentExpanded = false;
                            searchField.setText("/");

                        }
                        else{
                            Log.d("", "onGroupClick: if else");
                            parentExpanded = true;
                            System.out.println("POOOP");
                            listView.expandGroup(i);
                            searchField.setText("/" + parentList.get(i));
                            searchField.setSelection(parentList.get(i).length() + 1);
                        }
                    }
                    else{
                        Log.d("", "onGroupClick: else, pIndex: " + pIndex);
                        System.out.println(listView.isGroupExpanded(pIndex));
                        System.out.println("baajs");

                        listView.collapseGroup(pIndex);
                        /*if (listView.isGroupExpanded(pIndex)) {
                            System.out.println("collapse else, pIndex: "+ pIndex + " i: " + i);


                        }*/
                    }
                }


                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String text = "/" + parentList.get(i) +"/" +  data.get(i).second.get(i1);
                searchField.setText(text);
                searchField.setSelection(text.length());
                return false;
            }
        });
        searchField =  findViewById(R.id.search);
        searchField.setSelection(1);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                System.out.println("before text");
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
                    System.out.println("after if");
                    search(editable.toString());

                }
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

    public void collapseAll(){
        for(int i = 0; i < parentList.size(); i++){
            listView.collapseGroup(i);
        }
    }

    public void search(String query){

        searchField.setBackgroundColor(Color.WHITE);

        System.out.println("query: "+ query);
        List splitedString;
        splitedString = Arrays.asList(query.split("/", 3));
        ArrayList<String> potentialParents = potentialMatches(parentList, splitedString.get(1).toString());
        System.out.println("potentialParents: " + potentialParents);
        if (potentialParents.size() > 0){

            System.out.println("split result: " + splitedString.get(1));

            Integer parentMatchIndex =  match(parentList, splitedString.get(1).toString());
            //Parent match
            if (parentMatchIndex != null){
                System.out.println("expand that shit on parent index: " + parentMatchIndex);
                if(!listView.isGroupExpanded(parentMatchIndex) && !parentExpanded){
                    listView.expandGroup(parentMatchIndex);

                }

                if (splitedString.size() > 2){
                    ArrayList<String> potentialChildren = potentialMatches(data.get(parentMatchIndex).second, splitedString.get(2).toString());
                    //Child searched for
                    if(potentialChildren.size() > 0) {
                        Integer childMatchIndex = match(data.get(parentMatchIndex).second, splitedString.get(2).toString());
                        System.out.println("childmatchIndex: " + childMatchIndex);

                        //Child match
                        if (potentialChildren.size() > 0 && childMatchIndex != null) {
                            System.out.println("found the child!, parentIndex: " + parentMatchIndex + " childindex: " + childMatchIndex);
                            int index = listView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(parentMatchIndex, childMatchIndex));
                            System.out.println("flatend index: " + index);
                            listView.setItemChecked(index, true);
                        }
                    }
                    //No child match
                    else{
                        searchField.setBackgroundColor(Color.RED);
                    }
                }
            }


        }
        else{
            System.out.println("no parent match, RED");
            searchField.setBackgroundColor(Color.RED);

            for(int i= 0; i<parentList.size(); i++){
                listView.collapseGroup(i);
            }

        }
    }



    public ArrayList<String> potentialMatches(ArrayList<String> pMatches, String query){
        ArrayList<String> potentialMatchesIndex = new ArrayList<>();
        System.out.println("PMATCHES: " + pMatches);
        for(int i=0; i <pMatches.size(); i++){
            System.out.println("PMATCH: " + pMatches.get(i));
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

        ArrayList<String> life = new ArrayList<>();
        life.add("Falafel");
        life.add("Dragon");
        life.add("Dancing");
        life.add("Disco");

        ArrayList<String> food = new ArrayList<>();
        food.add("Hummus");
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
        //get reference to the ExpandableListView
        ExpandableListView listView = findViewById(R.id.ListView);
        //create the adapter by passing ArrayList data
        expandableListAdapter = new com.example.samuel.tddc73_lab2.ExpandableListAdapter(MainActivity.this, data);
        //attach the adapter to the list
        listView.setAdapter(expandableListAdapter);

    }
}
