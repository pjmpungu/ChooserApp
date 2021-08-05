package com.jonathannalikka.chooserapp;

import androidx.annotation.Nullable;
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

    //result code for when adding a new person

    public static final int add_person = 0204;

    //instance variables for ui components
    Button btnAdd, btnChoose;
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
        btnChoose = findViewById(R.id.btnChoose);
        recyclerView = findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //intialize array adapter

        myAdapter = new PersonAdapter(MainActivity.this, ApplicationClass.people);

        //set adapter for recylcer view

        recyclerView.setAdapter(myAdapter);

        //start to add new person when button is clicked

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivityForResult(intent, add_person);
            }
        });

        //when ready to choose at random go to new screen
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooserActivity.class);
                intent.putExtra("index", 0);
                startActivity(intent);

            }
        });

    }


    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, getString(R.string.surname)+ ApplicationClass.people.get(index).getLname(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if we are coming back from the add person screen, update our list

        if(requestCode == add_person && resultCode == NewPersonActivity.add_person_result_code){

            myAdapter.notifyDataSetChanged();

        }
    }
}