package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;

public class LifeCycle extends AppCompatActivity {
    ImageView growSpace,growMode,harvesting,seed,nutrients,monitoring,plantChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        growSpace=findViewById(R.id.grow_space);
        growSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",1);
                startActivity(intent);
            }
        });
        growMode=findViewById(R.id.grow_mode);
        growMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",2);
                startActivity(intent);*/
                {
                    Toast.makeText(LifeCycle.this,"Launching Soon",Toast.LENGTH_LONG).show();
                }
            }
        });

        plantChoice=findViewById(R.id.plant_choice);
        plantChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",3);
                startActivity(intent);*/

                    Toast.makeText(LifeCycle.this,"Launch Soon",Toast.LENGTH_LONG).show();

            }
        });

        seed=findViewById(R.id.seeding);
        seed.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",4);
                startActivity(intent);
               // Toast.makeText(LifeCycle.this,"Launching Soon",Toast.LENGTH_LONG).show();
            }
        });
        nutrients=findViewById(R.id.nutrition);
        nutrients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",5);
                startActivity(intent);*/
                Toast.makeText(LifeCycle.this,"Launching Soon",Toast.LENGTH_LONG).show();
            }
        });

        harvesting=findViewById(R.id.harvest);
        harvesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent  intent=new Intent (LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",6);
                startActivity(intent);*/
                Toast.makeText(LifeCycle.this,"Launching Soon",Toast.LENGTH_LONG).show();
            }
        });

        monitoring=findViewById(R.id.monitor);
        monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent  intent=new Intent (LifeCycle.this,GrowSpaceTutorialActivity.class);
                intent.putExtra("pos",7);
                startActivity(intent);*/
                Toast.makeText(LifeCycle.this,"Launching Soon",Toast.LENGTH_LONG).show();

            }
        });


    }
}

