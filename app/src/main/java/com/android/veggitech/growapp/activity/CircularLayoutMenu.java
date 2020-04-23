package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class CircularLayoutMenu extends AppCompatActivity {
        String TutorialName[]={"Grow Space","Grow Medium","Choice of Plants","Seeding","Water & Nutrition","Harvest"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_layout_menu);

        CircleMenu circleMenu=findViewById(R.id.cirle_menu);

        circleMenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.monitoring,R.drawable.remove_icon)
                    .addSubMenu(Color.parseColor("#ffffff"),R.drawable.circle_growspace)
                    .addSubMenu(Color.parseColor("#ffffff"),R.drawable.growmode)
                    .addSubMenu(Color.parseColor("#ffffff"),R.drawable.plantrow)
                   .addSubMenu(Color. parseColor("#ffffff"),R.drawable.seeding)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.watering)
                .addSubMenu(Color.parseColor("#ffffff"),R.drawable.harvest)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        Toast.makeText(CircularLayoutMenu.this,"You Selected " +TutorialName[index],Toast.LENGTH_LONG).show();
                    }
                });



    }
}
