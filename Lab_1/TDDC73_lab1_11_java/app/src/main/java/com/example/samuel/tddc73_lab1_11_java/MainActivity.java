package com.example.samuel.tddc73_lab1_11_java;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenWidth = 800;

        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Button myButton = new Button(this);
        myButton.setText("Knapp");
        myButton.setWidth(screenWidth);
        linearLayout.addView(myButton);

        EditText editText = new EditText(this);
        editText.setWidth(screenWidth);
        editText.setText("Text");
        linearLayout.addView(editText);

        RatingBar ratingBar = new RatingBar(this);
        ratingBar.setNumStars(5);
        ratingBar.setPadding(115,0,115,0);
        linearLayout.addView(ratingBar);

        EditText editTextMulti = new EditText(this);
        editTextMulti.setWidth(screenWidth);
        linearLayout.addView(editTextMulti);

        linearLayout.setGravity(Gravity.CENTER);

        constraintLayout.addView(linearLayout);
        setContentView(constraintLayout);
    }
}
