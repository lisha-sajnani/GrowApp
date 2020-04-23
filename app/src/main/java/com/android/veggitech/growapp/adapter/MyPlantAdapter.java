package com.android.veggitech.growapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.model.MyPlantModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyPlantAdapter extends ArrayAdapter<MyPlantModel> {

    Context context;
    private List<MyPlantModel> myPlantList;
    LayoutInflater layoutInflater;
    View view;
    ImageView imageView;
    TextView pName, pDays;
    ProgressBar progressBar;
    int num = 70;
    MyPlantModel myPlantModel;
    private final int image;
    String startDate, endDate, currentDate;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    Date sDate, eDate, cDate;
    String daysLeft;
    int max, progress;

    public MyPlantAdapter(@NonNull Context context, @NonNull List<MyPlantModel> myPlantList, int image) {
        super(context, R.layout.my_plant_item_list, myPlantList);
        this.context = context;
        this.myPlantList = myPlantList;
        this.image = image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.my_plant_item_list, parent, false);
        imageView = view.findViewById(R.id.imageViewMyPlant);
        imageView.setImageResource(image);
        pName = view.findViewById(R.id.textViewMyPlantName);
        pDays = view.findViewById(R.id.textViewMyPlantDays);
        progressBar = view.findViewById(R.id.myPlantProgress);

        myPlantModel = myPlantList.get(position);

        startDate = myPlantModel.getStartDate();
        endDate = myPlantModel.getEndDate();

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            sDate = dateFormat.parse(startDate);
            eDate = dateFormat.parse(endDate);

            long duration  = eDate.getTime() - sDate.getTime();
            float daysBetween = (duration / (1000*60*60*24));
            max = Math.round(daysBetween);
            //daysLeft = String.valueOf(daysBetween);

            currentDate = dateFormat.format(calendar.getTime());
            cDate = dateFormat.parse(currentDate);
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

        progressBar.setMax(max);
        progressBar.setProgress(progress);
        pName.setText(myPlantModel.getPlantName());
        pDays.setText(daysLeft +" Days Left");

        if(currentDate == endDate){
            pDays.setText("Harvest Today");
        }

        return view;
    }



    /*  Context context;
    private final String plantName;
    private final String plantDays;
    private final String startDate;
    private final String endDate;
    private final int [] images;
    LayoutInflater layoutInflater;
    View view;
    ImageView imageView;
    TextView pName, pDays;
    ProgressBar progressBar;
    int num = 70;

    public MyPlantAdapter(Context context, String plantName, String plantDays, String startDate, String endDate, int[] images) {
        this.context = context;
        this.plantName = plantName;
        this.plantDays = plantDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.my_plant_item_list, parent, false);
        imageView = view.findViewById(R.id.imageViewMyPlant);
        imageView.setImageResource(images[position]);
        pName = view.findViewById(R.id.textViewMyPlantName);
        pName.setText(plantName);
        pDays = view.findViewById(R.id.textViewMyPlantDays);
        pDays.setText(plantDays +" Days Left");
        progressBar = view.findViewById(R.id.myPlantProgress);
        progressBar.setMax(num);
        //progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#88d840"),
              //  android.graphics.PorterDuff.Mode.MULTIPLY);
        return view;
    }*/
}
