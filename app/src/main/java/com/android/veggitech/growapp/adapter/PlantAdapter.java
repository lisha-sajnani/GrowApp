package com.android.veggitech.growapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.activity.PlantActivity;
import com.android.veggitech.growapp.javaclass.ShareDialog;
import com.android.veggitech.growapp.model.FvtPlantModel;
import com.android.veggitech.growapp.model.MyPlantModel;
import com.android.veggitech.growapp.model.PlantItemModel;
import com.android.veggitech.growapp.model.SamplePlantModel;
import com.android.veggitech.growapp.model.TutorialModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class PlantAdapter extends BaseAdapter {

    Context context;
    String image, plantName, harvestPeriod;
    //private ArrayList<PlantItemModel> dataModelArrayList = new ArrayList<PlantItemModel>();
    LayoutInflater layoutInflater;
    ArrayList<SamplePlantModel> dataModelArrayList = new ArrayList<SamplePlantModel>();
    ArrayList<FvtPlantModel> fvtArrayList = new ArrayList<FvtPlantModel>();
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    FvtPlantModel favouritePlantmodel;
    public static final String SHARED_PREFS = "sharedPrefs";
    Boolean state;
    String plantId;
    Boolean fvtState;
    DatabaseReference stateDatabaseReference;
    RelativeLayout defaultView;
    LinearLayout swipeView1, swipeView2, swipeView3, swipeView;
    ImageView archive, favourite;
    View view;
    ImageView plant;
    PlantActivity plantActivity;
    //PlantItemModel plantItemModel;

    public PlantAdapter(Context context, ArrayList<SamplePlantModel> dataModelArrayList, Activity plantActivity){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
        this.plantActivity = (PlantActivity) plantActivity;
    }

    public void remove(int position) {
        dataModelArrayList.remove(position);
        notifyDataSetChanged();

    }

    public void swipeRight(int position) {
        defaultView.setVisibility(View.INVISIBLE);
        swipeView.setVisibility(View.VISIBLE);
    }

    public void swipeLeft(int position) {
        defaultView.setVisibility(View.VISIBLE);
        swipeView.setVisibility(View.INVISIBLE);

    }

    public void replace(int position) {

        defaultView.setVisibility(View.INVISIBLE);
        swipeView.setVisibility(View.VISIBLE);
       // swipeView2.setVisibility(View.VISIBLE);
       //swipeView3.setVisibility(View.VISIBLE);*/
        //dataModelArrayList.replaceAll(position);
        //notifyDataSetChanged();

      /*  if (defaultView.getVisibility() == View.VISIBLE) {
            fadeOutView(defaultView);
            slideInView(swipeView);
        } else {
            fadeInView(defaultView);
            slideOutView(swipeView);
        }*/
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup container) {
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.plant_item, container,
                false);

        plant = (ImageView) view.findViewById(R.id.imageViewPlant);
        image = dataModelArrayList.get(position).getImageUrl().toString();
        Picasso.get().load(image).into(plant);
        //plant.setImageResource(images[position]);

        TextView name = (TextView) view.findViewById(R.id.textViewPlantName);
        name.setText(dataModelArrayList.get(position).getPlantName().toString());
        TextView days = (TextView) view.findViewById(R.id.textViewDays);
        days.setText(dataModelArrayList.get(position).getPlantDays().toString()+" Days");
        TextView number = (TextView) view.findViewById(R.id.textViewPlantNumber);
        number.setText(dataModelArrayList.get(position).getPlantNumber().toString()+" Per Sqm");
        plantName = dataModelArrayList.get(position).getPlantName().toString();
        harvestPeriod = dataModelArrayList.get(position).getPlantDays().toString();

        defaultView = view.findViewById(R.id.defaultView);

        swipeView = view.findViewById(R.id.swipeView);

        swipeView1 = view.findViewById(R.id.swipeView1);
        archive = view.findViewById(R.id.image_archive);
        swipeView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                archive.setImageResource(R.drawable.ic_unarchive_white_24dp);
                Toast.makeText(context.getApplicationContext(), "Archived Clicked", Toast.LENGTH_LONG).show();
            }
        });

        swipeView2 = view.findViewById(R.id.swipeView2);
        favourite = view.findViewById(R.id.image_fvt);
        swipeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                favourite.setImageResource(R.drawable.ic_favorite_white_24dp);
                Toast.makeText(context.getApplicationContext(), "Fvt Clicked", Toast.LENGTH_LONG).show();
            }
        });

        swipeView3 = view.findViewById(R.id.swipeView3);
        swipeView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplicationInfo applicationInfo = context.getApplicationContext().getApplicationInfo();
                String apkPath = applicationInfo.sourceDir;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                context.startActivity(Intent.createChooser(intent, "ShareVia"));

               /* ShareDialog alert = new ShareDialog(image, plantName, harvestPeriod);
                alert.showDialog(plantActivity);*/
                //Toast.makeText(context.getApplicationContext(), "Share Clicked", Toast.LENGTH_LONG).show();
            }
        });

        final ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.toggleButtonFavourite);

        //String STORAGE_NAME = "mySharedPreferences";
        //sharedPreferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //sharedPreferences = context.getSharedPreferences("save",MODE_PRIVATE);
        //final SharedPreferences.Editor editor = sharedPreferences.edit();
        //toggleButton.setChecked(sharedPreferences.getBoolean("value", true));
        //toggleButton.setChecked(readState());


           //  fvtPlantname = dataModelArrayList.get(position).getPlantName();
          //   Toast.makeText(context.getApplicationContext(), "Plant Name is" + fvtPlantname, Toast.LENGTH_LONG).show();
            /* for(int j=0; j<dataModelArrayList.size(); j++){
                    plantName = dataModelArrayList.get(j).getPlantName();
                    if(fvtPlantname == plantName){
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtred));
                    }
                    else{
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtgray));
                    }
                }*/


      /*  databaseReference = FirebaseDatabase.getInstance().getReference("fvtplant");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fvtArrayList.clear();
                for(DataSnapshot plantDataSnapShot : dataSnapshot.getChildren()) {

                    String name = dataSnapshot.child("plantName").getValue(String.class);
                    favouritePlantmodel = plantDataSnapShot.getValue(FvtPlantModel.class);
                    fvtArrayList.add(favouritePlantmodel);
                    plantName = fvtArrayList.get(position).getPlantName();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        //Toast.makeText(context.getApplicationContext(), "Plant Name is" + plantName, Toast.LENGTH_LONG).show();
        state = dataModelArrayList.get(position).getState();
        if(state == true){

            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtred));
        }else{

            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtgray));
        }
       // toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtgray));


       // toggleButton.setOnCheckedChangeListener(null);
        //fvtState = toggleButton.isChecked();
       toggleButton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               //toggleButton.setChecked(false);
               fvtState = toggleButton.isChecked();
              // Toast.makeText(context, "Added to Favourite List" +fvtState  , Toast.LENGTH_LONG).show();
               plantId = dataModelArrayList.get(position).getPlantId().toString();
              // databaseReference=FirebaseDatabase.getInstance().getReference().child("plant").child(plantId);

               if(toggleButton.isChecked() == true){
                   //toggleButton.setChecked(true);
                   //toggleButton.clearFocus();
                   toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtred));

                   image = dataModelArrayList.get(position).getImageUrl().toString();
                   String pName = dataModelArrayList.get(position).getPlantName().toString();
                   String pDays = dataModelArrayList.get(position).getPlantDays().toString();
                   String pNumber = dataModelArrayList.get(position).getPlantNumber().toString();
                   databaseReference = FirebaseDatabase.getInstance().getReference("fvtplant");
                   String id = databaseReference.push().getKey();
                   FvtPlantModel fvtPlantModel = new FvtPlantModel(id, pName, pDays, pNumber, image);
                   databaseReference.child(id).setValue(fvtPlantModel);

                   plantId = dataModelArrayList.get(position).getPlantId().toString();
                   databaseReference=FirebaseDatabase.getInstance().getReference().child("plant").child(plantId);
                   databaseReference.child(plantId);
                   Map<String, Object> updates = new HashMap<String,Object>();
                   updates.put("state", true);
                   databaseReference.updateChildren(updates);

                   //toggleButton.setChecked(false);

                   //changePlantState(position, true);
                   //toggleButton.clearFocus();
                   Toast.makeText(context, "Added to Favourite List" , Toast.LENGTH_LONG).show();
               }
               else{

                   //toggleButton.setChecked(false);
                   //toggleButton.clearFocus();
                   toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.fvtgray));

                   plantId = dataModelArrayList.get(position).getPlantId().toString();
                   databaseReference=FirebaseDatabase.getInstance().getReference().child("plant").child(plantId);
                   Map<String, Object> updates = new HashMap<String,Object>();
                   updates.put("state", false);
                   databaseReference.updateChildren(updates);

                 // changePlantState(position, false);

                 /*   String fvtId = fvtArrayList.get(position).getFvtPlantId().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference("fvtplant");
                    databaseReference.child(fvtId).removeValue();*/

                   // toggleButton.setChecked(false);

                   Toast.makeText(context, "Removed from Favourite List" , Toast.LENGTH_LONG).show();
               }

              // toggleButton.setChecked(!toggleButton.isChecked());
               //toggleButton.setChecked(false);

           }
       });
       // ((ViewPager) container).addView(itemView, 0);
        return view;
    }

    private void changePlantState(int position, boolean checked) {

        plantId = dataModelArrayList.get(position).getPlantId().toString();
        stateDatabaseReference=FirebaseDatabase.getInstance().getReference().child("plant").child(plantId);
        Map<String, Object> updates = new HashMap<String,Object>();
        updates.put("state", checked);
        stateDatabaseReference.updateChildren(updates);
    }

    private void saveState(boolean isFavourite) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        sharedPreferences.edit().putBoolean("State", isFavourite).apply();
    }

    private boolean readState() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return true;
    }


    //Animation
    private void fadeOutView(View view) {
        Animation fadeOut = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_out);
        if (fadeOut != null) {
            fadeOut.setAnimationListener(new ViewAnimationListener(view) {
                @Override
                protected void onAnimationStart(View view, Animation animation) {

                }

                @Override
                protected void onAnimationEnd(View view, Animation animation) {
                    view.setVisibility(View.GONE);
                }
            });
            view.startAnimation(fadeOut);
        }
    }

    private void fadeInView(View view) {
        Animation fadeIn = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_in);
        if (fadeIn != null) {
            fadeIn.setAnimationListener(new ViewAnimationListener(view) {
                @Override
                protected void onAnimationStart(View view, Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onAnimationEnd(View view, Animation animation) {

                }
            });
            view.startAnimation(fadeIn);
        }
    }

    private void slideInView(View view) {
        Animation slideIn = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_in_right);
        if (slideIn != null) {
            slideIn.setAnimationListener(new ViewAnimationListener(view) {
                @Override
                protected void onAnimationStart(View view, Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onAnimationEnd(View view, Animation animation) {

                }
            });
            view.startAnimation(slideIn);
        }
    }

    private void slideOutView(View view) {
        Animation slideOut = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_out_right);
        if (slideOut != null) {
            slideOut.setAnimationListener(new ViewAnimationListener(view) {
                @Override
                protected void onAnimationStart(View view, Animation animation) {

                }

                @Override
                protected void onAnimationEnd(View view, Animation animation) {
                    view.setVisibility(View.GONE);
                }
            });
            view.startAnimation(slideOut);
        }
    }

    private abstract class ViewAnimationListener implements Animation.AnimationListener {

        private final View view;

        protected ViewAnimationListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            onAnimationStart(this.view, animation);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            onAnimationEnd(this.view, animation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        protected abstract void onAnimationStart(View view, Animation animation);

        protected abstract void onAnimationEnd(View view, Animation animation);
    }
}
