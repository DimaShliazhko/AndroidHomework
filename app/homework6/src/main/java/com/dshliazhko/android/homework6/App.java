package com.dshliazhko.android.homework6;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private Database db;
    public  static App instance;

    public Database getDb() {
        return db;
    }
    public static App getInstance() {
        return instance;
    }

    public void setDb(Database db) {
        this.db = db;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
      //  db = Room.databaseBuilder(getApplicationContext(),Database.class,"MyDB").allowMainThreadQueries().build();
       db = Room.databaseBuilder(getApplicationContext(),Database.class,"MyDB").build();
    }
}
