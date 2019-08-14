package com.example.mvvm_livedata_retro_room.service.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Location", indices = {@Index(value = "device_id", unique = true)})
public class LocationDto implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;

    @ColumnInfo(name = "device_id")
    @SerializedName("device_id")
    private String device_id;

    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    private String latitude;

    @ColumnInfo(name = "mobile")
    @SerializedName("mobile")
    private String mobile;

    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    private String longitude;

    protected LocationDto(Parcel in) {
//        id = in.readInt();
        date = in.readString();
        device_id = in.readString();
        latitude = in.readString();
        mobile = in.readString();
        longitude = in.readString();
    }

    public LocationDto() {
    }

    public static final Creator<LocationDto> CREATOR = new Creator<LocationDto>() {
        @Override
        public LocationDto createFromParcel(Parcel in) {
            return new LocationDto(in);
        }

        @Override
        public LocationDto[] newArray(int size) {
            return new LocationDto[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.device_id);
        dest.writeString(this.mobile);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }
}
