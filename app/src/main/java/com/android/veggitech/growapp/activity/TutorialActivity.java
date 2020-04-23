package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.adapter.TutorialAdapter;
import com.android.veggitech.growapp.model.TutorialModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class TutorialActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button skipTutorial;
    ArrayList<TutorialModel> arrayList;
    int images[] = {R.drawable.growspace, R.drawable.growmode, R.drawable.choice, R.drawable.seeding, R.drawable.water_nutrition, R.drawable.monitor, R.drawable.picking};
    String title[] = {"Grow Space", "Grow Mode", "Choice of Plants", "Seeding", "Water & Nutrition", "Monitoring", "Harvesting"};
    String description[] = {"The Grow Space  should get at least six hours of sunlight daily, good drainage and air circulation, and a level location with loose, rich soil.",
                            "The nutrients the plants need are provided by the nutrient solution, and is what the growing media is watered and moistened with. ",
    "Some important plant characteristics are size, hardiness, susceptibility to insects and diseases, and soil conditions. Careful plant selection can create an attractive landscape.",
    "A seed is the first stage in the life cycle of a plant. Protected inside the tough seed coat, or testa, is the baby plant, called an embryo. ",
    "Plants draw most of their nutrients through their roots. Often, water contains a significant amount of calcium, magnesium, sodium and chloride.",
    "Monitoring measures the plant growth parameters such as dimension - height and width, stem size of the plant, temperature and humidity.",
            "Most crops can be harvested several times. The quality of vegetables does not improve after harvesting, so it is important to gather crops at proper maturity."};
    Intent intent;
    private NavigationView navigationView;
    private DrawerLayout  drawer;
    private ActionBarDrawerToggle toggle;
    Intent navigationIntent;
    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = setupDrawerToggle();
        // Setup toggle to display hamburger icon with nice animation
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        // Tie DrawerLayout events to the ActionBarToggle
        drawer.addDrawerListener(toggle);

        //get firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Find our drawer view
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.home:
                                navigationIntent = new Intent(getApplicationContext(), TutorialActivity.class);
                                startActivity(navigationIntent);
                                break;

                            case R.id.plants:
                                navigationIntent = new Intent(getApplicationContext(), PlantActivity.class);
                                startActivity(navigationIntent);
                                break;
                            case R.id.grow_space:
                                navigationIntent = new Intent(getApplicationContext(), GrowSpaceActivity.class);
                                startActivity(navigationIntent);
                                break;
                            case R.id.favourite:
                                navigationIntent = new Intent(getApplicationContext(), FavouritePlantActivity.class);
                                startActivity(navigationIntent);
                                break;
                            case R.id.signOut:
                                signOut();
                                navigationIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(navigationIntent);
                                break;
                        }
                        return true;
                    }
                });


      /*  getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.veggitechlogo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/

        recyclerView = findViewById(R.id.recycler_view_tutorial);
        skipTutorial = findViewById(R.id.buttonSkip);
        skipTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), SetUpGrowSpaceActivity.class);
                startActivity(intent);
            }
        });
        arrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < images.length; i++) {
            TutorialModel tutorialModel = new TutorialModel();
            tutorialModel.setImage(images[i]);
            tutorialModel.setTitle(title[i]);
            tutorialModel.setDescription(description[i]);

            //add in array list
            arrayList.add(tutorialModel);
        }

        TutorialAdapter adapter = new TutorialAdapter(this.getApplicationContext(), arrayList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void signOut() {

        firebaseAuth.signOut();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.chat:
                String smsNumber = "971505537156"; //without '+'
                try {
                    Intent chatIntent = new Intent("android.intent.action.MAIN");
                    //chatIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    chatIntent.setAction(Intent.ACTION_SEND);
                    chatIntent.setType("text/plain");
                    chatIntent.putExtra(Intent.EXTRA_TEXT, "Your Message");
                    chatIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
                    chatIntent.setPackage("com.whatsapp");
                    startActivity(chatIntent);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.email:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {"cao@veggitech.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_CC, "it_projects@veggitech.com");
                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Send mail"));
                return true;

            case R.id.call:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:971505537156"));
                startActivity(phoneIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,  R.string.navigation_drawer_close);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }
}
