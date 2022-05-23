package com.example.davidharrisweatherapp.repository

import com.example.davidharrisweatherapp.api.ApiService
import com.example.davidharrisweatherapp.model.WeatherDataResponse

interface WeatherRepository {

    suspend fun forecastShown(city: String?): WeatherDataResponse

}

class WeatherRepositoryImpl(
    private val apiServices: ApiService = ApiService.apiMade()

) : WeatherRepository {

    override suspend fun forecastShown(city: String?): WeatherDataResponse {
        val response = apiServices.forecastMade(name = city)

        if (response.isSuccessful) {
            return response.body()!!
        }

        return WeatherDataResponse(emptyList())
    }
}
