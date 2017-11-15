package com.example.samuel.tddc73_lab1_13_java;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenWidth = 800;

        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        LinearLayout linearLayoutMain = new LinearLayout(this);
        linearLayoutMain.setOrientation(LinearLayout.VERTICAL);

        //Name fields
        LinearLayout linearLayoutName = new LinearLayout(this);
        linearLayoutName.setOrientation(LinearLayout.HORIZONTAL);

        TextView question1 = new TextView(this);
        question1.setText("Hur trivs du på LiU?");
        question1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayoutMain.addView(question1);


        LinearLayout linearLayoutQuestion1 = new LinearLayout(this);
        linearLayoutQuestion1.setOrientation(LinearLayout.HORIZONTAL);
        CheckBox checkBox1 = new CheckBox(this);
        checkBox1.setText("Bra");
        linearLayoutQuestion1.addView(checkBox1);
        CheckBox checkBox2 = new CheckBox(this);
        checkBox2.setText("Mycket bra");
        linearLayoutQuestion1.addView(checkBox2);
        CheckBox checkBox3 = new CheckBox(this);
        checkBox3.setText("Jättebra");
        linearLayoutQuestion1.addView(checkBox3);
        linearLayoutMain.addView(linearLayoutQuestion1);

        TextView question2 = new TextView(this);
        question2.setText("Läser du på LiTH?");
        question2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayoutMain.addView(question2);

        LinearLayout linearLayoutQuestion2 = new LinearLayout(this);
        linearLayoutQuestion2.setOrientation(LinearLayout.HORIZONTAL);
        CheckBox checkBox4 = new CheckBox(this);
        checkBox4.setText("Ja");
        linearLayoutQuestion2.addView(checkBox4);
        CheckBox checkBox5 = new CheckBox(this);
        checkBox5.setText("Nej");
        linearLayoutQuestion2.addView(checkBox5);
        linearLayoutMain.addView(linearLayoutQuestion2);

        ImageView imgView=(ImageView) findViewById(R.id.all);
        imgView.setImageResource("@drawable/liulogga");

        constraintLayout.addView(linearLayoutMain);
        setContentView(constraintLayout);
    }
}
