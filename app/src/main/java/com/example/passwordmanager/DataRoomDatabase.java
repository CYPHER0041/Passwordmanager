package com.example.passwordmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Data.class},version = 1,exportSchema = false)
public abstract class DataRoomDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
    private static volatile DataRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS); //runs db operations asynchronously in the background
    static DataRoomDatabase getDatabase(final Context context) { //getDatabase creates the Database
        //singleton: class in instantiated in only one place this prevents multiple instances of the database being opened at once
        if (INSTANCE == null) {
            synchronized (DataRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataRoomDatabase.class, "data_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
