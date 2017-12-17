package com.example.samuel.lab_3;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by samuel on 12/16/17.
 */


public class InteractiveSearch extends LinearLayout implements GetJson.AsyncResponse{
    Integer id = 0;
    ArrayList<String> textList = new ArrayList();


    public InteractiveSearch(Context context) throws JSONException {
        super(context);
        final InteractiveSearch interactiveSearch = this;

        EditText searchField = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        searchField.setLayoutParams(params);

        ListPopupWindow listPopupWindow = new ListPopupWindow(context);
        listPopupWindow.setAnchorView(searchField);
        listPopupWindow.setAdapter(new CustomListAdapter(textList, context));
        listPopupWindow.show();
        //Generates error!
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

                GetJson getJson = new GetJson(interactiveSearch);
                getJson.execute(editable.toString(), id.toString());
                id += 1;
            }
        });


    }

    @Override
    public void processFinish(JSONObject result) throws JSONException {
        JSONArray jsonArray = result.getJSONArray("result");
        ArrayList<String> resList = new ArrayList();
        for(int i = 0; i <jsonArray.length(); i ++){
            resList.add(jsonArray.get(i).toString());
        }

        String resId = result.get("id").toString();
        Integer tempId = id -1;
        if(resId.equals((tempId).toString())){
           textList = resList;
        }

        System.out.println("resultArray: " + resList);
        System.out.println("resultId: " + resId + " OGid: " + id);

    }

    //text paint new TextPaint
    //set text size = 60
    //array listadapter
    //lägg till med add i adaptern
    //ListPopupWindow listPopupWindow = new ListPopupWindow();
}
