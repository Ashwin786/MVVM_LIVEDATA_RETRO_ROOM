package com.example.mvvm_livedata_retro_room.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LocationList {
    @SerializedName("location")
    private ArrayList<LocationDto> location;

    @SerializedName("status")
    private String status;

    public ArrayList<LocationDto> getLocation ()
    {
        return location;
    }

    public void setLocation (ArrayList<LocationDto> location)
    {
        this.location = location;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
