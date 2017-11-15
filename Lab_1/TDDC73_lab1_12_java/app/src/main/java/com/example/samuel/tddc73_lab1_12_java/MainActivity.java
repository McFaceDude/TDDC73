package com.example.samuel.tddc73_lab1_12_java;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenWidth = 800;

        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);




        TextView question1 = new TextView(this);
        question1.setText("Hur trivs du på LiU?");
        question1.setMinWidth(150);
        linearLayoutMain.addView(question1);

        EditText editTextName = new EditText(this);
        editTextName.setText("Samuel");
        editTextName.setWidth(600);
        linearLayoutName.addView(editTextName);
        linearLayoutMain.addView(linearLayoutName);

        //Password fields
        LinearLayout linearLayoutPassword = new LinearLayout(this);
        linearLayoutPassword.setOrientation(LinearLayout.HORIZONTAL);

        TextView textViewPassword = new TextView(this);
        textViewPassword.setText("Password");
        textViewPassword.setMinWidth(150);
        linearLayoutPassword.addView(textViewPassword);

        EditText editTextPassword = new EditText(this);
        editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        editTextPassword.setWidth(600);
        linearLayoutPassword.addView(editTextPassword);
        linearLayoutMain.addView(linearLayoutPassword);

        //Email fields
        LinearLayout linearLayoutEmail = new LinearLayout(this);
        linearLayoutEmail.setOrientation(LinearLayout.HORIZONTAL);

        TextView textViewEmail = new TextView(this);
        textViewEmail.setText("Name");
        textViewEmail.setMinWidth(150);
        linearLayoutEmail.addView(textViewEmail);

        EditText editTextEmail = new EditText(this);
        editTextEmail.setText("samli627@student.liu.se");
        editTextEmail.setWidth(600);
        linearLayoutEmail.addView(editTextEmail);
        linearLayoutMain.addView(linearLayoutEmail);

        //Email fields
        LinearLayout linearLayoutAge = new LinearLayout(this);
        linearLayoutAge.setOrientation(LinearLayout.HORIZONTAL);

        TextView textViewAge = new TextView(this);
        textViewAge.setText("Name");
        textViewAge.setMinWidth(150);
        linearLayoutAge.addView(textViewAge);

        SeekBar seekBarAge = new SeekBar(this);
        //seekBarAge.setPadding(100,0,1,0);
        seekBarAge.setMinimumWidth(600);
        linearLayoutAge.addView(seekBarAge);
        linearLayoutMain.addView(linearLayoutAge);

        constraintLayout.addView(linearLayoutMain);
        setContentView(constraintLayout);
    }
}
