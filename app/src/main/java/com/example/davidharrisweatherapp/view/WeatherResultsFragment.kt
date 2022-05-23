package com.example.davidharrisweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.davidharrisweatherapp.databinding.FragmentWeatherListBinding
import com.example.davidharrisweatherapp.model.WeatherData
import com.example.davidharrisweatherapp.repository.WeatherRepositoryImpl
import com.example.davidharrisweatherapp.viewmodel.WeatherViewModel

class WeatherResultsFragment(private var city: String?) : Fragment() {
    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding get() = _binding!!

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(layoutInflater)

        binding.cityName.text = city
        vModel.getForecast(city)

        observerConfiguration()

        binding.infoBar.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }
    private val vModel: WeatherViewModel by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepositoryImpl()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    private fun makeChange(weatherList: WeatherData) {
        parentFragmentManager.beginTransaction()
            .replace(
                com.example.davidharrisweatherapp.R.id.v_container,
                WeatherDetailsFragment.instanceOfCity(weatherList, city)
            )
            .addToBackStack(null)
            .commit()
    }
    private fun observerConfiguration() {

        vModel.weatherCast.observe(viewLifecycleOwner) { response ->
            if (response.list.isNotEmpty()) {
                weatherAdapter.forecastMade(response.list)
                binding.allWeather.adapter = weatherAdapter
            }
        }
        weatherAdapter = WeatherAdapter(weatherData = ::makeChange)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}