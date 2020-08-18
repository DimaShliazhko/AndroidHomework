package com.dshliazhko.android.homeworkfour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Canvas canvas ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);


        ((CustomView) findViewById(R.id.customView)).setTouchAction(new CustomView.TouchAction() {
            @Override
            public void onTouchDown(int x, int y) {
                Toast.makeText(MainActivity.this, "нажаты координаты " + " X= " + x + " Y=" + y, Toast.LENGTH_SHORT).show();

                changed(x, y);

            }
        });

    }

    public void changed(int x, int y) {
        if (x >= 500 ) {
            CustomView customView = findViewById(R.id.customView);
            customView.getPaintSector1().setColor(getResources().getColor(R.color.colorDefolt));
            customView.change(canvas);
            //customView.invalidate();
            //  Toast.makeText(MainActivity.this, "CHANG", Toast.LENGTH_SHORT).show();

        }
    }


}


