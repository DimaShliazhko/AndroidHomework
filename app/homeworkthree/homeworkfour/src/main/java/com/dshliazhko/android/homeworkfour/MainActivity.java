package com.dshliazhko.android.homeworkfour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sPref;
    private Boolean savedState;
    private Boolean switchState;
    private Switch aSwitch;
    private Toolbar toolbar;

    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);
        aSwitch = findViewById(R.id.switchView);

        sPref = getPreferences(MODE_PRIVATE);
        savedState = sPref.getBoolean("seting", false);
        switchState = sPref.getBoolean("switchState", false);
        aSwitch.setChecked(switchState);


        // final SharedPreferences.Editor editor = sPref.edit();
        editor = sPref.edit();

        ((CustomView) findViewById(R.id.customView)).setTouchAction(new CustomView.TouchAction() {
            @Override
            public void onTouchDown(int x, int y) {
                CustomView customView = findViewById(R.id.customView);
                savedState = sPref.getBoolean("seting", false);
                customView.invalidate();

                if (savedState == true) {
                    Toast.makeText(MainActivity.this, "нажаты координаты " + " X= " + x + " Y=" + y, Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(customView, "нажаты координаты " + " X= " + x + " Y=" + y, Snackbar.LENGTH_LONG).show();
                }

            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("seting", b);
                //  editor.putBoolean("switchState", aSwitch.isChecked());
                editor.apply();
                //   Toast.makeText(MainActivity.this, "Отслеживание переключения: " + (b ? "on" : "off"), Toast.LENGTH_SHORT).show();
              /*  if (b == true) {

                    Toast.makeText(MainActivity.this, "Tost", Toast.LENGTH_SHORT).show();
                } else {

                    Snackbar.make(compoundButton, "Snackbar", Snackbar.LENGTH_LONG).show();
                }

*/
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Dima", "Destroi" + aSwitch.isChecked());
        editor.putBoolean("switchState", aSwitch.isChecked());
        editor = sPref.edit();

    }


}





