package com.example.mvvm_livedata_retro_room.service.repository.room_db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(LocationDto locationDto);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int update(LocationDto locationDto);

    @Query("Select * from Location")
    LiveData<List<LocationDto>> getAllLocation();

    @Query("Delete from Location")
    int delete();
}
