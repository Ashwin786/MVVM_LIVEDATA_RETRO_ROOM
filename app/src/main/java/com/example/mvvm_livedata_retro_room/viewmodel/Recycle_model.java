package com.example.mvvm_livedata_retro_room.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.service.repository.retrofit.Repository;

import java.util.ArrayList;

/**
 * Created by user1 on 31/7/19.
 */

public class Recycle_model extends ViewModel {

    private MutableLiveData<ArrayList<LocationDto>> locationList;

    public MutableLiveData<ArrayList<LocationDto>> getLocationList() {
        if (locationList == null)
            locationList = new MutableLiveData<ArrayList<LocationDto>>();
        return locationList;
    }


    public void init() {
        if (locationList != null)
            return;
        locationList = Repository.getInstance().getLocationList();
    }
}
