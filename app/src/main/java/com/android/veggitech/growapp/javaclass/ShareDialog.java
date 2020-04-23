package com.android.veggitech.growapp.javaclass;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.veggitech.growapp.R;
import com.android.veggitech.growapp.activity.LoginActivity;

import java.util.List;

public class ShareDialog {

String image, plantName, harvestPeriod;
Context context;
TextView cancel;
ImageView facebook, instagram, linkedIn, whatsApp, twitter, messenger;

    public ShareDialog(String image, String plantName, String harvestPeriod) {
        this.image = image;
        this.plantName = plantName;
        this.harvestPeriod = harvestPeriod;
    }

    public void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.share_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        cancel = (TextView) dialog.findViewById(R.id.textViewCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"Plant Name is " + plantName ,Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        dialog.show();

        facebook = (ImageView) dialog.findViewById(R.id.imageViewFacebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.cancel();
                //Toast.makeText(context.getApplicationContext(),"Plant Name is " ,Toast.LENGTH_SHORT).show();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.drawable.beetroot));
                shareIntent.putExtra(Intent.EXTRA_STREAM, image);

                PackageManager pm = v.getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList)
                {
                    if ((app.activityInfo.name).startsWith("com.facebook.katana"))
                    {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        v.getContext().startActivity(shareIntent);
                        break;
                    }
                }
            }
        });

    /*    instagram = dialog.findViewById(R.id.imageViewInstagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        linkedIn = dialog.findViewById(R.id.imageViewLinkedIn);
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        whatsApp = dialog.findViewById(R.id.imageViewWhatsApp);
        whatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        twitter = dialog.findViewById(R.id.imageViewTwitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        messenger = dialog.findViewById(R.id.imageViewMessenger);
        messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       /* WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.FIRST_SUB_WINDOW;
        layoutParams.height = WindowManager.LayoutParams.FIRST_SUB_WINDOW;
        dialog.getWindow().setAttributes(layoutParams);*/
    }
}
