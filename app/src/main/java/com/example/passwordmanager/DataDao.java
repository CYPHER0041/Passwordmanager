package com.example.passwordmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Data data); //inserts one row
    @Update void update(Data data);
    @Query("Delete from user_data") //delete the data
    void deleteAll();
    @Delete
    void deleteData(Data data); //delete single row
    @Query("Select * from user_data order by site_url ASC") //order data by url
    LiveData<List<Data>> getOrderedUrl();

}
