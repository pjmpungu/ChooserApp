package com.jonathannalikka.chooserapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChosenPersonActivity extends AppCompatActivity {

    TextView tvMessage;
    Button btnBack, btnSpin;
    ImageView ivPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_person);

        tvMessage = findViewById(R.id.tvMessage);

        getSupportActionBar().hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.75),(int)(height * 0.75));

        int index = getIntent().getIntExtra("index", 0);

        String fname = ApplicationClass.people.get(index).getFname();
        String lname = ApplicationClass.people.get(index).getLname();

        tvMessage.setText(getString(R.string.sorry) + fname + " " + lname + "!");
        btnBack = findViewById(R.id.btnBack);
        btnSpin = findViewById(R.id.btnSpinAgain);

        ivPicture = findViewById(R.id.ivPicture);
        ivPicture.setImageBitmap(ApplicationClass.people.get(index).getBitmap());

        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChosenPersonActivity.this, ChooserActivity.class));

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChosenPersonActivity.this, MainActivity.class));

            }
        });


    }
}