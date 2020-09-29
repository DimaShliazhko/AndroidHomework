package com.dshliazhko.android.homework6;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {ContactTable.class}, version = 1)
public abstract class Database extends RoomDatabase {

public abstract MyDAO getMyDAO();
}
