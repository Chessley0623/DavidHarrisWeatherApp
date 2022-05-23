package com.example.davidharrisweatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.davidharrisweatherapp.databinding.ForecastFragmentBinding
import com.example.davidharrisweatherapp.model.WeatherData

class WeatherAdapter(
    private val weather: MutableList<WeatherData> = mutableListOf(),
    private val weatherData: (WeatherData) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherAppViewHolder>() {


    inner class WeatherAppViewHolder(private val binding: ForecastFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherForecast: WeatherData) {
            binding.root.setOnClickListener {
                weatherData(weatherForecast)
            }

            binding.moreInfo.text = weatherForecast.weatherInfo()
            binding.thatTemp.text = weatherForecast.temperatureInfo()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAppViewHolder {
        return WeatherAppViewHolder(
            ForecastFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherAppViewHolder, position: Int) {
        holder.bind(weather[position])
    }

    override fun getItemCount(): Int {
        return weather.size
    }

    fun forecastMade(newList: List<WeatherData>) {
        weather.clear()
        weather.addAll(newList)
        notifyDataSetChanged()
    }
}