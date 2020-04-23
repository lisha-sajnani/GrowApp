package com.android.veggitech.growapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.adapter.PlantAdapter;
import com.android.veggitech.growapp.javaclass.BottomNavigationBehavior;
import com.android.veggitech.growapp.model.PlantItemModel;
import com.android.veggitech.growapp.model.SamplePlantModel;
import com.android.veggitech.growapp.model.TipModel;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlantActivity extends AppCompatActivity {

    int[] images = {R.drawable.cucumber, R.drawable.brinjal, R.drawable.tomato, R.drawable.kale, R.drawable.corn, R.drawable.carrot, R.drawable.zucchini, R.drawable.bellpepper, R.drawable.beetroot, R.drawable.radish, R.drawable.water, R.drawable.okra};
    String[] plantName = {"Cucumber", "Egg Plant", "Tomato", "Kale", "Sweet Corn", "Carrot", "Zucchini", "Capsicum", "Beetroot", "Radish", "Water Melon", "Okra"};
    String[] numberOfPlants = {"4 Per Sqm", "4 Per Sqm", "3 Per Sqm", "8 Per Sqm", "16 Per Sqm", "30 Per Sqm", "3 Per Sqm", "3 Per Sqm", "20 Per Sqm", "30 Per Sqm", "4 Per Sqm", "9 Per Sqm"};
    String[] plantDays = {"65 Days", "90 Days", "75 Days", "80 Days", "80 Days", "70 Days", "100 Days", "90 Days", "130 Days", "30 Days", "85 Days", "100 Days"};
    ListView listViewPlant;
    PlantAdapter plantAdapter;
    ArrayList<String> names;
    String string;
    private String urlString = "https://growspace.000webhostapp.com/plant";
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    JSONObject jsonObject;
    String pName, pNumber, pDays, pImage;
    ArrayList<PlantItemModel> dataModelArrayList = new ArrayList<PlantItemModel>();
    DatabaseReference databaseReference;
    ArrayList<SamplePlantModel> plantList = new ArrayList<SamplePlantModel>();
    SamplePlantModel samplePlantmodel;
    PlantItemModel plantItemModel;
    BottomNavigationView bottomNavigationView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBarPlantList);
        progressBar.setVisibility(View.INVISIBLE);

        listViewPlant = (ListView) findViewById(R.id.listViewPlant);

       // listViewPlant.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext(),listViewPlant));

        databaseReference = FirebaseDatabase.getInstance().getReference("plant");
       // retrieveJSON();
       // plantAdapter  = new PlantAdapter(PlantActivity.this, plantList, R.layout.plant_item,new String[]{"name","plantsqm","harvest"}, new int[]{R.id.textViewPlantName, R.id.textViewPlantNumber, R.id.textViewDays});
        //plantAdapter = new PlantAdapter(PlantActivity.this, plantName, numberOfPlants, plantDays, images);
        //listViewPlant.setAdapter(plantAdapter);
        listViewPlant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent plantIntent;
                switch (position){
                    case 0:
                        plantIntent = new Intent(PlantActivity.this, CucumberPlantDetailsActivity.class);
                        plantIntent.putExtra("Position", position);
                        startActivity(plantIntent);
                        break;
                    case 1:
                        //plantIntent = new Intent(PlantActivity.this, BrinjalPlantDetailsActivity.class);
                        //plantIntent.putExtra("Position", position);
                        //startActivity(plantIntent);
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 6:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 7:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 8:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 9:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 10:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;
                    case 11:
                        Toast.makeText(PlantActivity.this, "Launch Soon", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
    }

    private void retrieveJSON() {
        progressBar.setVisibility(View.VISIBLE);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonArrayRequest = new JsonArrayRequest(urlString, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                jsonObject = null;
                for(int i=0;i<response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        pName = jsonObject.getString("plant_name");
                        pDays = jsonObject.getString("harvest_period");
                        pNumber = jsonObject.getString("plants_sqm");
                        pImage = jsonObject.getString("plant_image");
                        //Toast.makeText(getActivity(), "Data" +name, Toast.LENGTH_LONG).show();
                        plantItemModel = new PlantItemModel();


                        plantItemModel.setPlantName(pName);
                        plantItemModel.setHarvestPeriod(pDays);
                        plantItemModel.setPlantSqm(pNumber);
                        plantItemModel.setImageUrl(pImage);

                        dataModelArrayList.add(plantItemModel);

                        setupListview();
                        //mProgressDialog.dismiss();
                        //Toast.makeText(getActivity(), "Data" +tipModel.toString() , Toast.LENGTH_LONG).show();

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void setupListview() {
        //plantAdapter = new PlantAdapter(getApplicationContext(), dataModelArrayList);
        //listViewPlant.setAdapter(plantAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                plantList.clear();
                for(DataSnapshot plantDataSnapShot : dataSnapshot.getChildren()) {

                    samplePlantmodel = plantDataSnapShot.getValue(SamplePlantModel.class);
                    plantList.add(samplePlantmodel);
                }

                plantAdapter = new PlantAdapter(getApplicationContext(), plantList, PlantActivity.this);
                listViewPlant.setAdapter(plantAdapter);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final SwipeToDismissTouchListener<ListViewAdapter> touchListener = new SwipeToDismissTouchListener<>(
                new ListViewAdapter(listViewPlant),
                new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListViewAdapter recyclerView, int position) {
                       plantAdapter.replace(position);

                    }
                });

        listViewPlant.setOnTouchListener(touchListener);
        listViewPlant.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
        //touchListener.

    }

  /*  public class OnSwipeTouchListener implements View.OnTouchListener {


        private final String TAG = OnSwipeTouchListener.class.getSimpleName();
        ListView list;
        private GestureDetector gestureDetector;
        private Context context;


        public OnSwipeTouchListener(Context ctx, ListView list) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
            context = ctx;
            this.list = list;
        }

        public OnSwipeTouchListener() {
            super();
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        public void onSwipeRight(int pos) {
            //Do what you want after swiping left to right

            //Log.e(TAG, "onSwipeRight: " + pos);

            plantAdapter.swipeRight(pos);
        }

        public void onSwipeLeft(int pos) {

            //Do what you want after swiping right to left
            //Log.e(TAG, "onSwipeLeft: " + pos);
            plantAdapter.swipeLeft(pos);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            private int getPostion(MotionEvent e1) {
                return list.pointToPosition((int) e1.getX(), (int) e1.getY());
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY)
                        && Math.abs(distanceX) > SWIPE_THRESHOLD
                        && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight(getPostion(e1));
                    else
                        onSwipeLeft(getPostion(e1));
                    return true;
                }
                return false;
            }

        }


    }*/
}

