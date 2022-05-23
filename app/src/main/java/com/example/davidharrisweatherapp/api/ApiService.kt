package com.example.davidharrisweatherapp.api

import com.example.davidharrisweatherapp.model.WeatherDataResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val Weather_KEY = "47dfc22ea9059197ffe40851f4e7e90c"

interface ApiService {
    @GET("forecast")
    suspend fun forecastMade(
        @Query("q") name: String? = null,
        @Query("appid") apiKey: String = Weather_KEY
    ): Response<WeatherDataResponse>

    companion object {
        private var instance: ApiService? = null
        fun apiMade(): ApiService {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
            return instance!!
        }
    }
}