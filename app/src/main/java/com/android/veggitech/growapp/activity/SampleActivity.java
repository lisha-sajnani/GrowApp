package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.javaclass.CircleLayout;
import com.android.veggitech.growapp.model.SamplePlantModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SampleActivity extends AppCompatActivity {

    RelativeLayout center;
    Button savePlant;
    DatabaseReference databaseReference;
    String [] imageUrl = {"https://www.quick-garden.co.uk/wp/wp-content/uploads/2018/05/Everything-You-Need-to-Know-about-Cucumber-Growing05301.jpg", "https://www.growdynamix.com/wp-content/uploads/2018/09/eggplant-black-beauty.jpg", "https://www.sweetvalleyherbs.ca/wp-content/uploads/tomato-beefmaster.jpg", "https://www.rareseeds.com/media/catalog/product/cache/4f71e30e38ffe1b90b59b74efe76a4b8/K/a/Kale-Blue-Curled-Scotch-LSS-DSC_0532.jpg", "https://tuigarden.co.nz/media/2645/sweetcorn_growing_guide.png", "https://www.plant-world-seeds.com/images/item_images/000/005/742/large_square/ROMANCE_F1.JPG?1495393594", "https://cdn.shopify.com/s/files/1/0004/2588/5749/products/bsc_golden_zucchini_1200x1200.jpg?v=1520616344", "https://edge.bonnieplants.com/www/uploads/20180920004013/pimiento-pepper.jpg", "https://cdn.mr-fothergills.co.uk/product-images/op/z/19220z.jpg", "https://www.plant-world-seeds.com/images/item_images/000/006/931/large_square/VIOLA_RADISH.JPG?1495395234",  "https://www.veseys.com/media/catalog/product/cache/image/700x700/e9c3970ab036de70892d86c6d221abfe/m/i/minilove-15751a-image1-15751_minilove.jpg", "https://gardenerspath.com/wp-content/uploads/2019/04/Favorites-Okra-Types-to-Grow-at-Home.jpg"};
    String[] plantName = {"Cucumber", "Egg Plant", "Tomato", "Kale", "Sweet Corn", "Carrot", "Zucchini", "Capsicum", "Beetroot", "Radish", "Water Melon", "Okra"};
    String[] plantNumber = {"4", "4", "3", "8", "16", "30", "3", "3", "20", "30", "4", "9"};
    String[] plantDays = {"65", "90", "75", "80", "80", "70", "100", "90", "130", "30", "85", "100"};
    Boolean[] state = {false, false, false, false, false, false, false, false, false, false, false, false};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        /*center = findViewById(R.id.relativeCenter);
        center.bringToFront();
        center.invalidate();
       // CircleLayout circleLayout = new CircleLayout(getApplicationContext());

       /* savePlant = findViewById(R.id.buttonSample);
        savePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<plantName.length; i++){

                    databaseReference = FirebaseDatabase.getInstance().getReference("plant");
                    String id = databaseReference.push().getKey();
                    SamplePlantModel plantModel = new SamplePlantModel(id, plantName[i], imageUrl[i], plantDays[i], plantNumber[i], state[i]);
                    databaseReference.child(id).setValue(plantModel);
                    Toast.makeText(getApplicationContext(), "Plant Data Entered", Toast.LENGTH_LONG).show();
                }

            }
        });*/
    }
}
