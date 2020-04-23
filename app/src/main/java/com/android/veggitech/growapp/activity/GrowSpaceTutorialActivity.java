package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.adapter.GrowFragmentPagerAdapter;
import com.android.veggitech.growapp.fragment.GrowModeFragment;
import com.android.veggitech.growapp.fragment.GrowModeTutorialFragment;
import com.android.veggitech.growapp.fragment.GrowSapceTutorialFragment;
import com.android.veggitech.growapp.fragment.GrowSpaceVideoFragment;
import com.android.veggitech.growapp.fragment.SeedingTutorialFragment;
import com.android.veggitech.growapp.fragment.SetupGrowSpaceFragment;
import com.android.veggitech.growapp.fragment.TipDetailsFragment;
import com.android.veggitech.growapp.javaclass.BottomNavigationBehavior;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class GrowSpaceTutorialActivity extends AppCompatActivity {

    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageView growSpace, growMode;
    TextView textView;
    TabLayout tabLayout;
    ViewPager viewPager;
    GrowFragmentPagerAdapter pagerAdapter;
    BottomNavigationView bottomNavigationView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_space_tutorial);

        bottomNavigationView = findViewById(R.id.navigation);
        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.growSpace:
                        intent = new Intent(GrowSpaceTutorialActivity.this, SetUpGrowSpaceActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.video:
                        fragment = new GrowSpaceVideoFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        // fragment.setArguments(bundle);
                        fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                }
                return false;
            }
        });

     /*   fragment = new GrowSapceTutorialFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
       // fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();*/

        Bundle extras;
        extras = getIntent().getExtras();
        if (extras != null) {
           int position = extras.getInt("pos");
            if (position == 1) {


                fragment = new GrowSapceTutorialFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                // fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();


            }
       /* if(position==2){
           *//* fragment = new GrowModeTutorialFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();*//*

            Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();

        }*/
            if(position==3){
               /* fragment = new GrowSapceTutorialFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                // fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();*/
               // Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();

            }

            if(position==4){
                //Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();
                fragment = new SeedingTutorialFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                // fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();

            }

            if(position==5){
               /*  fragment = new GrowModeTutorialFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            // fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_container_grow_tutorial, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
*/            //  Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();

            }


            if(position==6)
            {
              //  Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();
            }

            if(position==7)
            {
                //Toast.makeText(GrowSpaceTutorialActivity.this,"Launch Soon",Toast.LENGTH_LONG).show();
            }

        }
    }
}


