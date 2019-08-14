package com.example.mvvm_livedata_retro_room.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvm_livedata_retro_room.R;
import com.example.mvvm_livedata_retro_room.service.model.LocationDto;

public class EditActivity extends AppCompatActivity {

    private String Tag = "EditActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Bundle data = getIntent().getExtras();
        LocationDto locationDto = (LocationDto) data.getParcelable("location");
        Log.e(Tag,locationDto.toString());
    }
}
