package com.example.samuel.lab_3;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;

/**
 * Created by samuel on 12/16/17.
 */


public class InteractiveSearch extends LinearLayout implements GetJson.AsyncResponse{

    public InteractiveSearch(Context context) throws JSONException {
        super(context);
        EditText searchField = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        searchField.setLayoutParams(params);
        String url = "http://getnames-getnames.a3c1.starter-us-west-1.openshiftapps.com/getnames/1/Emm";
        GetJson getJson = new GetJson(this);
        getJson.execute(url);
        //System.out.println(json.getString("result"));
        //ArrayList res = json.get("result");
        addView(searchField);


    }

    @Override
    public void processFinish(String result) {
        System.out.println("result: " + result);
    }

    //text paint new TextPaint
    //set text size = 60
    //array listadapter
    //lägg till med add i adaptern
    //ListPopupWindow listPopupWindow = new ListPopupWindow();
}
