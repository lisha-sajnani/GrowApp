package com.android.veggitech.growapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.veggitech.growapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CucumberAttackFragment extends Fragment {

    public CucumberAttackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cucumber_fragment_attack, container, false);
    }

}
