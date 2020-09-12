package com.dshliazhko.android.homeworkfive;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDAO {

    @Query("SELECT * FROM ContactTable")
    List<ContactTable> getAll();

    @Query("SELECT * FROM ContactTable where edit_name like :s || '%'")
    List<ContactTable> search(String s);

    @Insert
    void insert(ContactTable contact);

    @Delete
    void delete(ContactTable contact);

    @Update
    void update(ContactTable contact);

}
