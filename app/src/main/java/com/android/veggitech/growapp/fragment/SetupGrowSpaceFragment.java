package com.android.veggitech.growapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.activity.GrowModeActivity;
import com.android.veggitech.growapp.activity.GrowSpaceActivity;
import com.android.veggitech.growapp.activity.SignupActivity;
import com.android.veggitech.growapp.database.DbHandler;
import com.android.veggitech.growapp.model.GrowSpaceModel;
import com.android.veggitech.growapp.model.MyPlantModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetupGrowSpaceFragment extends Fragment {

    Button createGrowSpace;
    Spinner locationSpinner, growSpaceSpinner;
    EditText length, width, growSpaceName;
    String location, growLength, growWidth, growName;
    Intent intent;
    Double gLength, gWidth;
    FloatingActionButton floatingActionButton;
    RelativeLayout growSpace, setUpGrowSpace;
    DatabaseReference databaseReference;
    View view;
    ProgressBar progressBar;

    public SetupGrowSpaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_setup_grow_space, container, false);


        growSpaceName = view.findViewById(R.id.editTextGrowSpaceName);
        length = view.findViewById(R.id.editTextLength);
        length.setHint("Length (m)");
        width = view.findViewById(R.id.editTextWidth);
        width.setHint("Width (m)");
        growSpace = view.findViewById(R.id.growSpaceName);
        setUpGrowSpace = view.findViewById(R.id.setUpGrowSpace);
        floatingActionButton = view.findViewById(R.id.floating_growSpace);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                floatingActionButton.setVisibility(INVISIBLE);
                growSpace.setVisibility(INVISIBLE);
                setUpGrowSpace.setVisibility(View.VISIBLE);
            }
        });

        createGrowSpace = view.findViewById(R.id.buttonCreate);
        createGrowSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                location = locationSpinner.getSelectedItem().toString();
                growLength = length.getText().toString();
                gLength = Double.parseDouble(growLength);
                growWidth = width.getText().toString();
                gWidth = Double.parseDouble(growWidth);
                growName = growSpaceName.getText().toString();
                if(TextUtils.isEmpty(growName)){
                    Toast.makeText(getActivity(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(growLength)){
                    Toast.makeText(getActivity(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(growWidth)){
                    Toast.makeText(getActivity(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }
                
                addGrowSpace(growName, location, gLength, gWidth);
              /*  if(growName == null && growLength ==null && growWidth == null){
                    Toast.makeText(getActivity(), "Please fill all fields!!!", Toast.LENGTH_LONG).show();
                }*/
               /* DbHandler dbHandler = new DbHandler(getActivity());
                dbHandler.insertGrowSpaceDetails(location,gLength,gWidth);*/
                Toast.makeText(getActivity(), "Your Grow Space Created Successfully...",Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(), GrowModeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void addGrowSpace(String growName, String location, Double gLength, Double gWidth) {

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String userId = currentFirebaseUser.getUid();
        String id = databaseReference.push().getKey();
        GrowSpaceModel growSpaceModel = new GrowSpaceModel(userId, id, growName, location, gLength, gWidth);
        databaseReference.child(id).setValue(growSpaceModel);
    }

    @Override
    public void onStart() {
        super.onStart();

        progressBar = view.findViewById(R.id.progressBarGrowSpace);
        progressBar.setVisibility(View.VISIBLE);
        databaseReference = FirebaseDatabase.getInstance().getReference("mygrowspace");

        growSpaceSpinner = view.findViewById(R.id.spinnerGrowSpaceName);
        growSpaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    case 1:
                        intent = new Intent(getActivity(), GrowSpaceActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), GrowSpaceActivity.class);
                        startActivity(intent);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        locationSpinner = view.findViewById(R.id.spinnerLocation);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> growNameList = new ArrayList<String>();

                growNameList.add("Select One");

                for (DataSnapshot growSpaceSnapshot: dataSnapshot.getChildren()) {
                    String growName = growSpaceSnapshot.child("growSpaceName").getValue(String.class);
                    if (growName!=null){
                        growNameList.add(growName);
                    }
                }
                ArrayAdapter<String> growSpaceAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, growNameList);
                growSpaceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //growSpaceSpinner.setPrompt("Select One");
                //growSpaceAdapter.add("Select One");
                growSpaceSpinner.setAdapter(growSpaceAdapter);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
