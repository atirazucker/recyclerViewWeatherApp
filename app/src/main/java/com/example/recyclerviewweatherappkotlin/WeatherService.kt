package com.example.recyclerviewweatherappkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/group?")
    fun getCurrentWeather(@Query("id")city_id:String,
                          @Query("appid")app_id:String,
                          @Query("units")weather_units: String): Call<List<WeatherResponse>>

}