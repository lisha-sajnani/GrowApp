package com.android.veggitech.growapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.veggitech.growapp.R;

public class GrowSpaceVideoActivity extends AppCompatActivity {

    VideoView videoViewIntro, videoViewSeeding, videoViewTransplanting;
    MediaController mediaController;
    String videoPathIntro = "android.resource://com.android.veggitech.growapp/"+R.raw.intro;
    String videoPathSeeding = "android.resource://com.android.veggitech.growapp/"+R.raw.seeding;
    String videoPathTransplant = "android.resource://com.android.veggitech.growapp/"+R.raw.transplanting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grow_space_video);

        mediaController = new MediaController(getApplicationContext());

       /* viewPager = view.findViewById(R.id.viewPagerVideo);
        TutorialVideoAdapter videoAdapter = new TutorialVideoAdapter(getActivity());
        viewPager.setAdapter(videoAdapter);

        circleIndicator = view.findViewById(R.id.indicatorVideo);
        circleIndicator.setViewPager(viewPager);*/

        videoViewIntro = findViewById(R.id.videoViewIntro);
        Uri uri = Uri.parse(videoPathSeeding);
        videoViewIntro.setVideoURI(uri);
        videoViewIntro.setMediaController(mediaController);
        mediaController.setAnchorView(videoViewIntro);
        videoViewIntro.start();

    }
}
