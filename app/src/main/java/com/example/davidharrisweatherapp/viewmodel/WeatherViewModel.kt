package com.example.davidharrisweatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.davidharrisweatherapp.model.WeatherDataResponse
import com.example.davidharrisweatherapp.repository.WeatherRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val repositoryImpl: WeatherRepositoryImpl) : ViewModel() {
    private val _weatherCast = MutableLiveData<WeatherDataResponse>()
    val weatherCast: LiveData<WeatherDataResponse> get() = _weatherCast

    fun getForecast(city: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryImpl.forecastShown(city)
            _weatherCast.postValue(response)
        }
    }
}