package com.dshliazhko.android.mytest;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("MainActivity2","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }
}
