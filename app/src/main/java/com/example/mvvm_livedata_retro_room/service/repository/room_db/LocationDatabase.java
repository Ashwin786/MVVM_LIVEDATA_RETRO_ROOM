package com.example.mvvm_livedata_retro_room.service.repository.room_db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;

@Database(entities = {LocationDto.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {
    private static final String DB_NAME = "location.db";

    public abstract LocationDao locationDao();

    private static LocationDatabase instances;

    public static LocationDatabase getDatabase(Context context) {
        if (instances == null) {
//            instances =
            Builder<LocationDatabase> db_builder = Room.databaseBuilder(context.getApplicationContext(), LocationDatabase.class, DB_NAME);
            db_builder.allowMainThreadQueries();
            instances = db_builder.build();
        }
        return instances;
    }
}
