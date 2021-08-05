package com.jonathannalikka.chooserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChooserActivity extends AppCompatActivity {

    LuckyWheel luckyWheel;
    List<WheelItem> wheelItemList = new ArrayList<>();
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        luckyWheel = findViewById(R.id.luckyWheel);
        rand = new Random();

        boolean primary = true;
        for(Person person: ApplicationClass.people){

            if(primary) {
                wheelItemList.add(new WheelItem(ResourcesCompat.getColor(getResources(), R.color.accent, null),
                        person.getBitmap(),
                        person.getFname() + "  " + person.getLname()));
                primary = false;
            }else{

                wheelItemList.add(new WheelItem(ResourcesCompat.getColor(getResources(), R.color.primary, null),
                        person.getBitmap(),
                        person.getFname() + "  " + person.getLname()));
                primary = true;

            }

        }

        luckyWheel.addWheelItems(wheelItemList);
        int index = rand.nextInt(ApplicationClass.people.size());
        luckyWheel.setTarget(index+1);

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {

                Intent intent = new Intent(ChooserActivity.this, ChosenPersonActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);

            }
        });
    }

}