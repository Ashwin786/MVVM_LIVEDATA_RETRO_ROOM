package com.example.mvvm_livedata_retro_room.service.repository.room_db;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;

import java.util.ArrayList;

public class InsertAsyncTask extends AsyncTask<ArrayList<LocationDto>, Void, Void> {

    private final LocationDao locationDao;

    public InsertAsyncTask(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    @Override
    protected Void doInBackground(ArrayList<LocationDto>... arrayLists) {
        for (LocationDto dto : arrayLists[0]) {
            Log.e("Insertion : ", "" + locationDao.insert(dto));
        }
        return null;
    }
}
