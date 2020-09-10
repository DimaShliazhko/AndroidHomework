package com.dshliazhko.android.homeworkfive;

import androidx.room.Dao;
import androidx.room.RoomDatabase;
@androidx.room.Database(entities = {ContactTable.class}, version = 1)
public abstract class Database extends RoomDatabase {

public abstract MyDAO getMyDAO();
}
