package com.example.samuel.tddc73_lab1_11_java;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout CL = new ConstraintLayout(this);

        LinearLayout LL = new LinearLayout(this);
        LL.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams LLparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        //myll.setLayoutParams(LinearLayout.WRAP_CONTENT, LinearLayout.WRAP_CONTENT);
        CL.setLayoutParams(params);

        Button myButton = new Button(this);

        myButton.setText("Knapp");
        LL.addView(myButton);

        TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setText("Text");
        LL.addView(textView);



        CL.addView(LL);

        setContentView(CL);


    }
}
