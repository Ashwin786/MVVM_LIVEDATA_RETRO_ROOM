package com.example.mvvm_livedata_retro_room.service.repository.retrofit;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.service.model.LocationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private final APIInterface apiInterface;
    static Repository repository;

    public Repository() {
        apiInterface = RetroFit_Service.getClient().create(APIInterface.class);
    }

    public MutableLiveData<ArrayList<LocationDto>> getLocationList() {
        final MutableLiveData<ArrayList<LocationDto>> mutableLiveData = new MutableLiveData<>();
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
                mutableLiveData.setValue(null);
                call.cancel();
            }
        });
        return mutableLiveData;
    }


    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }
}
