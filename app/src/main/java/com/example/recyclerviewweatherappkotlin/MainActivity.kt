package com.example.recyclerviewweatherappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    fun getData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeather(Id,
            AppId,
            Units)

        call.enqueue(object : Callback<List<WeatherResponse>>{
            override fun onFailure(call: Call<List<WeatherResponse>>, t: Throwable) {
                t.localizedMessage
            }

            override fun onResponse(call: Call<List<WeatherResponse>>, response: Response<List<WeatherResponse>>) {
                if(response.code() == 200){
                    val weatherResponse = response.body()

                    weatherResponse?.let {
                        showWeather(it)
                    }
                }
            }
        })
    }

    private fun showWeather(weather: List<WeatherResponse>){
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdapter(weather)
    }

    companion object{
        val BaseURL = "https://api.openweathermap.org/"
        val AppId = "2d17eb81ea9383f61f865d637b2e9770"
        val Id = "293725,294071,294502,293198,295410"
        val Units = "metric"
    }
}