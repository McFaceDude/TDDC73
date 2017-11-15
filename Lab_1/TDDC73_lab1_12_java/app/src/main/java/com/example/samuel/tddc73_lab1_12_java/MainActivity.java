package com.example.samuel.tddc73_lab1_12_java;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenWidth = 800;

        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);

        LinearLayout linearLayoutName = new LinearLayout(this);
        linearLayoutName.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutMain.addView(linearLayoutName);

        TextView textViewName = new TextView(this);
        textViewName.setText("Name");
        linearLayoutName.addView(textViewName);
        EditText editTextName = new EditText(this);
        editTextName.setText("Samuel");
        linearLayoutName.addView(editTextName);

        LinearLayout linearLayoutPassword = new LinearLayout(this);
        linearLayoutPassword.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutMain.addView(linearLayoutPassword);

        TextView textViewPassword = new TextView(this);
        textViewPassword.setText("Password");
        linearLayoutName.addView(textViewPassword);
        EditText editTextPassword = new EditText(this);
        editTextPassword.setInputType(-1);
        linearLayoutName.addView(editTextPassword);


        constraintLayout.addView(linearLayoutMain);
        setContentView(constraintLayout);
    }
}
