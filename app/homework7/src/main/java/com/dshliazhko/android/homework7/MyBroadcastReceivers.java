package com.dshliazhko.android.homework7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;

public class MyBroadcastReceivers extends BroadcastReceiver {

    private String folderPath;
    private File file;
    private Context context;
    private String s1 = Intent.ACTION_TIMEZONE_CHANGED;
    private String s2 = Intent.ACTION_AIRPLANE_MODE_CHANGED;
    private String s3 = Intent.ACTION_CAMERA_BUTTON;
    private SharedPreferences sPref;


    @Override
    public void onReceive(Context context, Intent intent) {

        String intentString = intent.getAction();


        if (intentString.equals(s1) || intentString.equals(s2) || intentString.equals(s3)) {
            //   Log.d("Dima", "BroadCast " + date + " " + intent.getAction());
            sPref = context.getSharedPreferences("DIMA",Context.MODE_PRIVATE);
            Intent intent1 = new Intent(context, MyService.class);
            intent1.putExtra("BroadcastReceiver", intent.getAction());
            intent1.putExtra("folderPath", sPref.getString("folderPath","internal"));


            context.startService(intent1);


        }
    }
}
