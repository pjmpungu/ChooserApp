package com.jonathannalikka.chooserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {

    //instance variables for ui components
    Button btnAdd;
    TextView tvTitle;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize ui instance variables

        btnAdd = findViewById(R.id.btnAdd);
        tvTitle = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //initialize array list which stores people

        people = new ArrayList<Person>();
        people.add(new Person());

        //intialize array adapter

        myAdapter = new PersonAdapter(MainActivity.this, people);

        //set adapter for recylcer view

        recyclerView.setAdapter(myAdapter);

        //start to add new person when button is clicked

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, "Surname "+ people.get(index).getLname(), Toast.LENGTH_SHORT).show();
    }

}