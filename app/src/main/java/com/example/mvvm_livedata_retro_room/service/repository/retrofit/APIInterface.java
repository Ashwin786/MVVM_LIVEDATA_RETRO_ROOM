package com.example.mvvm_livedata_retro_room.service.repository.retrofit;


import com.example.mvvm_livedata_retro_room.service.model.LocationList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user1 on 8/5/19.
 */

public interface APIInterface {
    //    @GET("/api/unknown")
    @GET("/get_all_location.php")
    Call<LocationList> doGetLocationList();


    //    @POST("/get_location.php")
//    Call<LocationDto> doGetLocation(@Body LocationDto location);
//    @Headers("Content-Type: application/x-www-form-urlencoded")
    /*@FormUrlEncoded
    @POST("/get_location.php")
    Call<Location_Request> doGetLocation(@FieldMap HashMap<String, String> data);
*/
   /* @POST("/login/validateuser")
    Call<Location_Request> doGetLogin(@Body LoginDto dto);

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);*/
}
