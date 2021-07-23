package com.aiosep.stormrazor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Location.class,exportSchema = false,version = 1)
public abstract class LocationDatabase extends RoomDatabase {

    private static final String DB_NAME="location_db";
    private static LocationDatabase instance;
    public static synchronized LocationDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),LocationDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract LocationDao locationDao();
}
