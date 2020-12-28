package com.example.mvvm_livedata_retro_room.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.R;
import com.example.mvvm_livedata_retro_room.service.model.LocationDto;
import com.example.mvvm_livedata_retro_room.service.repository.Repository;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.InsertAsyncTask;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.LocationDao;
import com.example.mvvm_livedata_retro_room.service.repository.room_db.LocationDatabase;
import com.example.mvvm_livedata_retro_room.view.adapter.CustomAdapter;
import com.example.mvvm_livedata_retro_room.viewmodel.Recycle_model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private ArrayList<LocationDto> locationArrayList = new ArrayList<>();
    private Recycle_model model;
    private CustomAdapter adapter;
    private RecyclerView recyclerview;
    private LocationDatabase locationDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intializing recyclerview*/
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(llm);

        /*Intializing room database*/
        locationDb = LocationDatabase.getDatabase(this);

        /*Intializing view model*/
        model = ViewModelProviders.of(this).get(Recycle_model.class);


        /*Intializing live data observer*/
        Observer<ArrayList<LocationDto>> listObserver = new Observer<ArrayList<LocationDto>>() {
            @Override
            public void onChanged(@Nullable final ArrayList<LocationDto> list) {
                // Update the UI, in this case, a TextView.
                Log.e(TAG, "Observer called");
                if (list == null || list.size() == 0)
                    return;

                locationArrayList.addAll(list);

                /*Checking is adapter is null or intialized*/
                if (adapter != null) {
                    /*Refresing the recyclerview*/
                    adapter.notifyDataSetChanged();
                } else {
                    /*Intializing the adapter and setting to recyclerview*/
                    adapter = new CustomAdapter(MainActivity.this, locationArrayList);
                    recyclerview.setAdapter(adapter);
                }

            }
        };
        /*Intializing live data observer*/
        Observer<List<LocationDto>> dbObserver = new Observer<List<LocationDto>>() {
            @Override
            public void onChanged(@Nullable final List<LocationDto> list) {
                // Update the UI, in this case, a TextView.
                Log.e(TAG, "dbObserver called");
                if (list == null || list.size() == 0)
                    return;
                Log.e(TAG, "onChanged: " + list.size());
                locationArrayList.clear();

                locationArrayList.addAll(list);
                /*Checking is adapter is null or intialized*/
                if (adapter != null) {
                    /*Refresing the recyclerview*/
                    adapter.notifyDataSetChanged();
                } else {
                    /*Intializing the adapter and setting to recyclerview*/
                    adapter = new CustomAdapter(MainActivity.this, locationArrayList);
                    recyclerview.setAdapter(adapter);
                }
            }
        };
        /*Attaching observer to live data*/
//        model.getLocationList().observe(this, listObserver);


        Repository.getInstance().getLocationListFromServer(LocationDatabase.getDatabase(this).locationDao());
        model.getDbData().observe(this, dbObserver);
//        model.getSeverData();

        Observer<List<LocationDto>> testObserver = new Observer<List<LocationDto>>() {
            @Override
            public void onChanged(@Nullable final List<LocationDto> list) {

            }
        };
        model.getLocationList().observe(this, testObserver);

    }
}
