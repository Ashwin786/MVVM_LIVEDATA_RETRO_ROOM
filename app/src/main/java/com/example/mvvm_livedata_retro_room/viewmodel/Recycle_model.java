package com.example.mvvm_livedata_retro_room.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.service.repository.Repository;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.LocationDao;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.LocationDatabase;
import com.example.mvvm_livedata_retro_room.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user1 on 31/7/19.
 */

public class Recycle_model extends AndroidViewModel {

    private static final String TAG = "Recycle_model";
    private final LocationDao locationDao;
    private LiveData<List<LocationDto>> locationList;

    public Recycle_model(@NonNull Application application) {
        super(application);
        locationDao = LocationDatabase.getDatabase(application).locationDao();

    }

    public LiveData<List<LocationDto>> getLocationList() {
//        if (locationList == null)
//            locationList = new MutableLiveData<List<LocationDto>>();
        return Repository.getInstance().getLocationList(locationDao);
    }

    public void getSeverData() {
        Repository.getInstance().getLocationListFromServer(locationDao);
    }

    public LiveData<List<LocationDto>> getDbData() {
//        locationDao.delete();
        return Repository.getInstance().getLocationListFromDb(locationDao);
    }
   /* public void init() {
        if (locationList != null)
            return;
        locationList = Repository.getInstance().getLocationList();
    }*/
}
