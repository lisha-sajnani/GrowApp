package com.android.veggitech.growapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.activity.MyPlantDetailsActivity;
import com.android.veggitech.growapp.adapter.MyPlantAdapter;
import com.android.veggitech.growapp.database.DbHandler;
import com.android.veggitech.growapp.model.MyPlantModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPlantFragment extends Fragment {

    int image = R.drawable.cucumber;
   // String[] plantName = {"Cucumber"};
    //int[] plantDays = {70};
    String plantName;
    String plantDays, startDate, endDate, currentDate;
    ListView listViewMyPlant;
    MyPlantAdapter plantAdapter;
    TextView textView;
    Intent detailsIntent;
    Bundle bundle;
    private DbHandler handler;
    private SQLiteDatabase dataBase;
    ArrayList<MyPlantModel> arrayList = new ArrayList<MyPlantModel>();
    DatabaseReference databaseReference;
    MyPlantModel myPlantModel = new MyPlantModel();
    List<MyPlantModel> myPlantlist ;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    Date sDate, eDate, cDate;
    String daysLeft;
    int max, progress;
    private ProgressDialog progressDialog;
    ProgressBar progressBar;

    public MyPlantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_plant, container, false);
        textView = view.findViewById(R.id.textViewText);
        //getPlantData();

        progressBar = view.findViewById(R.id.progressBarMyplant);
        progressBar.setVisibility(View.INVISIBLE);
        listViewMyPlant = view.findViewById(R.id.listViewMyPlant);
        listViewMyPlant.setEmptyView(textView);
        myPlantlist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("myplant");
        //getPlantData();
        //listViewMyPlant.setVisibility(View.INVISIBLE);
       /* bundle = getArguments();
        plantName = bundle.getString("name");
        if(plantName == null){
            listViewMyPlant.setVisibility(View.INVISIBLE);
        }else{
            listViewMyPlant.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
        }
        plantDays = bundle.getString("days");
        startDate = bundle.getString("start");
        endDate = bundle.getString("end");*/

        //Toast.makeText(getActivity(), "Plant Name is"  +plantName +plantDays +startDate +endDate, Toast.LENGTH_LONG).show();

        //plantAdapter = new MyPlantAdapter(getActivity(), plantName, plantDays, startDate, endDate, images);
        //listViewMyPlant.setAdapter(plantAdapter);

        listViewMyPlant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myPlantModel = myPlantlist.get(position);
                startDate = myPlantModel.getStartDate();
                endDate = myPlantModel.getEndDate();

                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                currentDate = dateFormat.format(calendar.getTime());
                try {
                    cDate = dateFormat.parse(currentDate);
                    eDate = dateFormat.parse(endDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(currentDate ==endDate || cDate.after(eDate)){
                    //delete query
                }


                try {
                    sDate = dateFormat.parse(startDate);
                    eDate = dateFormat.parse(endDate);

                    long duration  = eDate.getTime() - sDate.getTime();
                    float daysBetween = (duration / (1000*60*60*24));
                    max = Math.round(daysBetween);
                    //daysLeft = String.valueOf(daysBetween);


                    //cDate = dateFormat.parse(currentDate);
                    long leftDuration = eDate.getTime() - cDate.getTime();
                    float leftDays = (leftDuration / (1000*60*60*24));
                    int days = Math.round(leftDays);
                    daysLeft = String.valueOf(days);

                    long progressDays = cDate.getTime() - sDate.getTime();
                    float pDays = (progressDays / (1000*60*60*24));
                    progress = Math.round(pDays);

                    //Toast.makeText(context,"Date is" +progress, Toast.LENGTH_LONG).show();
                    //System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                detailsIntent = new Intent(getActivity(), MyPlantDetailsActivity.class);
                detailsIntent.putExtra("start",startDate);
                detailsIntent.putExtra("end", endDate);
                detailsIntent.putExtra("max", max);
                detailsIntent.putExtra("progress", progress);
                startActivity(detailsIntent);
                //Toast.makeText(getActivity(), "Date is " + startDate, Toast.LENGTH_LONG).show();
               /* switch (position){
                    case 0:
                       detailsIntent = new Intent(getActivity(), MyPlantDetailsActivity.class);
                       detailsIntent.putExtra("start",startDate);
                       detailsIntent.putExtra("end", endDate);
                       startActivity(detailsIntent);
                }*/
            }
        });
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        textView.setVisibility(View.INVISIBLE);
        //progressDialog = new ProgressDialog(getActivity());
        //progressDialog.setTitle("Uploading...");
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myPlantlist.clear();
                for(DataSnapshot plantDataSnapShot : dataSnapshot.getChildren()){

                    myPlantModel = plantDataSnapShot.getValue(MyPlantModel.class);
                    myPlantlist.add(myPlantModel);
                }

                plantAdapter = new MyPlantAdapter(getActivity(), myPlantlist, image);
                listViewMyPlant.setAdapter(plantAdapter);
                //progressDialog.dismiss();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

    private void getPlantData() {

        DbHandler db = new DbHandler(getActivity());
        Cursor cursor = db.getData();
        if (cursor.moveToFirst()){
            do{
                String data = cursor.getString(0);
                Toast.makeText(getActivity(), "Plant Name is"  +data, Toast.LENGTH_LONG).show();
                // do what ever you want here
            }while(cursor.moveToNext());
        }
        cursor.close();
       // arrayList = db.GetMyPlants();



      /*  handler = new DbHandler(getActivity());
        dataBase = handler.getWritableDatabase();
        Cursor cursor = handler.getData();
        if(cursor.moveToFirst()){
            Toast.makeText(getActivity(), "Plant Name is", Toast.LENGTH_LONG).show();
        }*
        //the SQL command to fetched all records from the table
        /*Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
                + DbHandler.TABLE_MY_PLANT, null);
        MyPlantModel myPlantModel = new MyPlantModel();
        //fetch each record
        if (mCursor.moveToFirst()) {
            do {
                //get data from field
                // stafid.add(mCursor.getString(mCursor.getColumnIndex(DbHandler.)));
                // nama.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.NAMA)));
                // jbt.add(mCursor.getString(mCursor.getColumnIndex(DBHelper.JBT)));
                plantName = mCursor.getString(0);
                Toast.makeText(getActivity(), "Plant Name is" +plantName, Toast.LENGTH_LONG).show();

            } while (mCursor.moveToNext());
            //do above till data exhausted
        }*/
    }
}
