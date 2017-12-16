package com.example.samuel.lab_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayoutMain = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayoutMain.setLayoutParams(params);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);

        InteractiveSearch searchField = null;
        try {
            searchField = new InteractiveSearch(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        linearLayoutMain.addView(searchField);
        setContentView(linearLayoutMain);
    }

}
