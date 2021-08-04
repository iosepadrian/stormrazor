package com.aiosep.stormrazor;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM location")
    List<Location> getLocationList();


    @Insert
    void insertLocation(Location location);
    @Update
    void updateLocation(Location location);
    @Delete
    void deleteLocation(Location location);



}
