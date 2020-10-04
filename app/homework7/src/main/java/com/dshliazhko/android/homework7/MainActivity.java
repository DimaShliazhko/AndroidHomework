package com.dshliazhko.android.homework7;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;
    private Switch aSwitch;

    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;
    private Boolean switchState;
    private String folderPath;
    private String fileName = "BroadcastReceivers";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        aSwitch = findViewById(R.id.switchStorage);
        sPref = getSharedPreferences("DIMA",MODE_PRIVATE);
        switchState = sPref.getBoolean("switchState", true);
        aSwitch.setChecked(switchState);
        editor = sPref.edit();
        editor.apply();


     //   startService(new Intent(this, MyService.class));
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b == false) {

                  //  folderPath = MainActivity.this.getFilesDir().toString();
                    folderPath = "internal";
                } else {
/*
                    File sdCardfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
                    // создаем каталог
                    if (!sdCardfile.exists()) {
                        sdCardfile.mkdirs();
                    }
*/
                  //  folderPath = Environment.getExternalStorageDirectory().getAbsoluteFile().toString();
                    folderPath = "external";
                }
                Log.d("Dima", "путь " + folderPath);
                editor.putBoolean("switchState", b);
                editor.putString("folderPath",folderPath);
                editor.apply();

            }

        });
        receiver = new MyBroadcastReceivers();
      //  receiver = new MyBroadcastReceivers();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
         filter.addAction(Intent.ACTION_CAMERA_BUTTON);
        this.registerReceiver(receiver, filter);




    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}





