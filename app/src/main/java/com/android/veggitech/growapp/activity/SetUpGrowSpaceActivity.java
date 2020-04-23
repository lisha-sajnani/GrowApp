package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.fragment.GrowSapceTutorialFragment;
import com.android.veggitech.growapp.fragment.GrowSpaceVideoFragment;
import com.android.veggitech.growapp.fragment.SetupGrowSpaceFragment;
import com.android.veggitech.growapp.javaclass.BottomNavigationBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.view.View.INVISIBLE;

public class SetUpGrowSpaceActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RelativeLayout growSpace, setUpGrowSpace;
    FloatingActionButton floatingActionButton;
    EditText length, width, growSpaceName;
    Button createGrowSpace;
    Spinner locationSpinner;
    String location, growName, growLength, growWidth;
    Double gLength, gWidth;
    Intent intent;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_grow_space);

        bottomNavigationView = findViewById(R.id.navigation);
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tutorial:
                        intent = new Intent(SetUpGrowSpaceActivity.this, GrowSpaceTutorialActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.video:
                        intent = new Intent(SetUpGrowSpaceActivity.this, GrowSpaceVideoActivity.class);
                        startActivity(intent);
                      /*  fragment = new GrowSpaceVideoFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        // fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.linear1, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();*/
                        break;
                }
                return false;
            }
        });

       /* locationSpinner = findViewById(R.id.spinnerLocation);
        growSpaceName = findViewById(R.id.editTextGrowSpaceName);
        length = findViewById(R.id.editTextLength);
        length.setHint("Length (m)");
        width = findViewById(R.id.editTextWidth);
        width.setHint("Width (m)");
        growSpace = findViewById(R.id.growSpaceName);
        setUpGrowSpace = findViewById(R.id.setUpGrowSpace);
        floatingActionButton = findViewById(R.id.floating_growSpace);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                floatingActionButton.setVisibility(INVISIBLE);
                growSpace.setVisibility(INVISIBLE);
                setUpGrowSpace.setVisibility(View.VISIBLE);
            }
        });

        createGrowSpace = findViewById(R.id.buttonCreate);
        createGrowSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                location = locationSpinner.getSelectedItem().toString();
                growLength = length.getText().toString();
                gLength = Double.parseDouble(growLength);
                growWidth = width.getText().toString();
                gWidth = Double.parseDouble(growWidth);
                growName = growSpaceName.getText().toString();
                if(growName == null){
                    Toast.makeText(getApplicationContext(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                else if(growLength == null){
                    Toast.makeText(getApplicationContext(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                else if(growWidth == null){
                    Toast.makeText(getApplicationContext(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                else if(growName == null && growLength ==null && growWidth == null){
                    Toast.makeText(getApplicationContext(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
               /* DbHandler dbHandler = new DbHandler(getActivity());
                dbHandler.insertGrowSpaceDetails(location,gLength,gWidth);*/
              /*  Toast.makeText(getApplicationContext(), "Your Grow Space Created Successfully...",Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), GrowModeActivity.class);
                startActivity(intent);
            }
        });*/

        fragment = new SetupGrowSpaceFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        // fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.linear1, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
