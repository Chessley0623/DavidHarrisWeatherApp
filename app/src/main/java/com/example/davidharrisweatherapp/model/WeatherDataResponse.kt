package com.example.davidharrisweatherapp.model


import android.os.Parcelable
import com.example.davidharrisweatherapp.MainActivity
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlin.math.roundToInt

data class WeatherDataResponse(val list: List<WeatherData>)

@Parcelize
data class WeatherData(
    val weather: @RawValue List<WeatherList>,
    val main: Main,
    val name: String
) : Parcelable {

    private fun temperatureConverter(temp: Double): String {

        val temperature: Double = when (MainActivity.floatButton?.text) {
            "°F" -> ((temp * 1.8) - 459.67)
            "°C" -> (temp - 273.15)
            else -> temp
        }

        return (temperature.roundToInt()).toString()
    }

    fun temperature(): String {
        return temperatureConverter(this.main.temp)
    }

    fun temperatureInfo(): String {

        println(MainActivity.floatButton?.text)

        return "Temp: " + temperatureConverter(this.main.temp)
    }

    fun weatherFeels(): String {
        return "Weather feels like: " + temperatureConverter(this.main.feels_like)
    }

    fun weatherInfo(): String {
        return this.weather[0].main
    }

    fun weatherMoreInfo(): String {
        return this.weather[0].description
    }

}

data class WeatherList(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Parcelize
data class Main(
    val temp: Double,
    val feels_like: Double
) : Parcelable
