package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.fragment.MyPlantDetailsFragment;
import com.android.veggitech.growapp.fragment.TipDetailsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyPlantDetailsActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FloatingActionButton floatingActionButton;
    Intent intent;
    String startDate, endDate;
    int max, progress;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plant_details);

        startDate = getIntent().getStringExtra("start");
        endDate = getIntent().getStringExtra("end");
        max = getIntent().getIntExtra("max", 0);
        String m = Integer.toString(max);
        progress =  getIntent().getIntExtra("progress", 0);
        String p = Integer.toString(progress);

        bundle.putString("start", startDate);
        bundle.putString("end", endDate);
        bundle.putString("max", m);
        bundle.putString("progress", p);
        //Toast.makeText(getApplicationContext(), "max " + max, Toast.LENGTH_LONG).show();

        floatingActionButton = findViewById(R.id.fab_monitor);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), PlantGrowthMonitorActivity.class);
                startActivity(intent);
            }
        });

        fragment = new MyPlantDetailsFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
