package com.example.samuel.lab_3;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by samuel on 12/16/17.
 */


public class InteractiveSearch extends LinearLayout implements GetJson.AsyncResponse{
    Integer id = 0;
    Integer maxItems = 10;
    ArrayList<String> textList = new ArrayList();
    ListPopupWindow listPopupWindow;
    CustomListAdapter customListAdapter;

    public InteractiveSearch(Context context) throws JSONException {
        super(context);
        final InteractiveSearch interactiveSearch = this;

        final EditText searchField = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        searchField.setLayoutParams(params);
        listPopupWindow = new ListPopupWindow(context);

        listPopupWindow.setAnchorView(searchField);
        customListAdapter = new CustomListAdapter(textList, context);
        listPopupWindow.setAdapter(customListAdapter);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchField.setText(adapterView.getAdapter().getItem(i).toString());
                searchField.setSelection(adapterView.getAdapter().getItem(i).toString().length());
            }
        });

        addView(searchField);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0){
                    GetJson getJson = new GetJson(interactiveSearch);
                    getJson.execute(editable.toString(), id.toString());
                    id += 1;
                }
                else{
                    listPopupWindow.dismiss();
                    id += 1;
                }
            }
        });


    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        ArrayList<String> resList = new ArrayList();
        Integer itemsToShow;
        try {
            jsonArray = result.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();

        }

        if(jsonArray.length() < maxItems){
            itemsToShow = jsonArray.length();
        }
        else{
            itemsToShow = maxItems;
        }

        if(jsonArray.length() > 0){
            for(int i = 0; i <itemsToShow; i ++){
                resList.add(jsonArray.get(i).toString());
            }
        }

        String resId = result.get("id").toString();
        Integer tempId = id - 1;
        if(resId.equals((tempId).toString())){
            System.out.println("match!");
           textList = resList;
        }
        customListAdapter.clearItems();
        for(String item: textList){
            System.out.println("add item: "+ item);
            customListAdapter.addItem(item);
        }

        listPopupWindow.show();

        System.out.println("resultArray: " + textList);
        System.out.println("resultId: " + resId + " OGid: " + tempId);

    }
}
