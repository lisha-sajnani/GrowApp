package com.android.veggitech.growapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.model.FvtPlantModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FvtPlantAdapter extends BaseAdapter {

    Context context;
   // private final String [] plantName;
   // private final String [] numberOfPlants;
   // private final String [] plantDays;
   // private final int [] images;
    LayoutInflater layoutInflater;
    ArrayList<FvtPlantModel> arrayList = new ArrayList<FvtPlantModel>();
    FvtPlantModel fvtPlantModel = new FvtPlantModel();
    String image;
    String pName, Pdays, pNumber;

    public FvtPlantAdapter(Context context, ArrayList<FvtPlantModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        final View view = layoutInflater.inflate(R.layout.fvt_plant_list_item, parent, false);

        ImageView plant = (ImageView) view.findViewById(R.id.imageViewPlant);
        image = arrayList.get(position).getImageUrl().toString();
        Picasso.get().load(image).into(plant);

        TextView name = (TextView) view.findViewById(R.id.textViewPlantName);
        name.setText(arrayList.get(position).getPlantName().toString());
        TextView days = (TextView) view.findViewById(R.id.textViewDays);
        days.setText(arrayList.get(position).getPlantDays().toString() + " Days");
        TextView number = (TextView) view.findViewById(R.id.textViewPlantNumber);
        number.setText(arrayList.get(position).getPlantNumber().toString() + " Per Sqm");
        return view;
    }
}
