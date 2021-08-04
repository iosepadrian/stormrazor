package com.aiosep.stormrazor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiService {

    @GET("locations")
    Call<List<Location>> getLocationList();

    @GET("location/search/")
    Call<List<LocationEntity>> getLocation(@Query("query") String name);
    @GET("location/search")
    Call<List<LocationEntity>> getLocationByLatLong(@Query("lattlong") String lattlong);

    @GET("location/{woeid}")
    Call<LocationEntity> getLocationDetails(@Path("woeid") String woeid);


}
