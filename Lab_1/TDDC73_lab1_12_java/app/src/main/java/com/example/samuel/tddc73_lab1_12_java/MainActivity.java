package com.example.samuel.tddc73_lab1_12_java;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {

    int textHeight = 80;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setWeightSum(7);
        linearLayoutMain.setOrientation(LinearLayout.HORIZONTAL);

        //Left column
        LinearLayout linearLayoutLeftColumn = new LinearLayout(this);
        linearLayoutLeftColumn.setOrientation(LinearLayout.VERTICAL);
        linearLayoutLeftColumn.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 2));
        linearLayoutMain.addView(linearLayoutLeftColumn);

        //Right column
        LinearLayout linearLayoutRightColumn = new LinearLayout(this);
        linearLayoutRightColumn.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRightColumn.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 5));
        linearLayoutMain.addView(linearLayoutRightColumn);

        //Params for left column texts
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, textHeight);

        //Text fält
        TextView textViewName = new TextView(this);
        textViewName.setText("Name");
        textViewName.setGravity(Gravity.CENTER_VERTICAL);
        textViewName.setLayoutParams(textParams);
        linearLayoutLeftColumn.addView(textViewName);

        EditText editTextName = new EditText(this);
        editTextName.setText("Samuel");
        linearLayoutRightColumn.addView(editTextName);

        //Password
        TextView textViewPassword = new TextView(this);
        textViewPassword.setText("Password");
        textViewPassword.setLayoutParams(textParams);
        textViewPassword.setGravity(Gravity.CENTER_VERTICAL);
        linearLayoutLeftColumn.addView(textViewPassword);

        EditText editTextPassword = new EditText(this);
        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        linearLayoutRightColumn.addView(editTextPassword);

        //Email fields
        TextView textViewEmail = new TextView(this);
        textViewEmail.setText("Email");
        textViewEmail.setLayoutParams(textParams);
        textViewEmail.setGravity(Gravity.CENTER_VERTICAL);
        linearLayoutLeftColumn.addView(textViewEmail);

        EditText editTextEmail = new EditText(this);
        editTextEmail.setText("samli627@student.liu.se");
        linearLayoutRightColumn.addView(editTextEmail);

        //Age fields
        TextView textViewAge = new TextView(this);
        textViewAge.setText("Age");
        textViewAge.setLayoutParams(textParams);
        textViewAge.setGravity(Gravity.CENTER_VERTICAL);
        linearLayoutLeftColumn.addView(textViewAge);

        SeekBar seekBarAge = new SeekBar(this);
        linearLayoutRightColumn.addView(seekBarAge);

        setContentView(linearLayoutMain);
    }
}
