package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.veggitech.growapp.R;

public class GrowSpaceActivity extends AppCompatActivity {

    ImageView arrowDown;
    RelativeLayout rightEnd;
    boolean showingFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_space);

        rightEnd = findViewById(R.id.secondRightEnd);
        arrowDown = findViewById(R.id.arrowDown);
        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingFirst == true){
                    arrowDown.setImageResource(R.drawable.ic_arrow_drop_up_green_24dp);
                    rightEnd.setVisibility(View.VISIBLE);
                    showingFirst = false;
                }else{
                    arrowDown.setImageResource(R.drawable.ic_arrow_drop_down_green_24dp);
                    rightEnd.setVisibility(View.GONE);
                    showingFirst = true;
                }

            }
        });


    }
}
