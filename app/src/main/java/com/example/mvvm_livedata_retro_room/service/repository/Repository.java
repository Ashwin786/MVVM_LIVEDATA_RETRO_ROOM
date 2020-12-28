package com.example.mvvm_livedata_retro_room.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.service.model.LocationList;
import com.example.mvvm_livedata_retro_room.service.repository.retrofit.APIInterface;
import com.example.mvvm_livedata_retro_room.service.repository.retrofit.RetroFit_Service;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.InsertAsyncTask;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.LocationDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private final APIInterface apiInterface;
    static Repository repository;

   private MutableLiveData<List<LocationDto>> mutableLiveData =  new MutableLiveData<List<LocationDto>>();
    public Repository() {
        apiInterface = RetroFit_Service.getClient().create(APIInterface.class);
    }

    public LiveData<List<LocationDto>> getLocationList(final LocationDao locationDao) {
        Call<LocationList> call = apiInterface.doGetLocationList();
        call.enqueue(new Callback<LocationList>() {
            @Override
            public void onResponse(Call<LocationList> call, final Response<LocationList> response) {
                Log.e("Code", response.code() + "");
                if (response.code() == 200) {
                    LocationList location = response.body();
                    mutableLiveData.setValue(location.getLocation());
                }
            }

            @Override
            public void onFailure(Call<LocationList> call, Throwable t) {
                call.cancel();
            }
        });

        return mutableLiveData;
    }

    public void getLocationListFromServer(final LocationDao locationDao) {
        Call<LocationList> call = apiInterface.doGetLocationList();
        call.enqueue(new Callback<LocationList>() {
            @Override
            public void onResponse(Call<LocationList> call, final Response<LocationList> response) {
                Log.e("Code", response.code() + "");
                if (response.code() == 200) {
                    LocationList location = response.body();
//                    mutableLiveData.setValue(location.getLocation());
                    /*Inserting into database*/
                    new InsertAsyncTask(locationDao).execute(location.getLocation());
                }
            }

            @Override
            public void onFailure(Call<LocationList> call, Throwable t) {
                call.cancel();
            }
        });
    }


    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }


    public LiveData<List<LocationDto>> getLocationListFromDb(LocationDao locationDao) {
        return locationDao.getAllLocation();
    }
}
