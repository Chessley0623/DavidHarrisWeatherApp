package com.example.davidharrisweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.davidharrisweatherapp.databinding.WeatherDetailsFragmentBinding
import com.example.davidharrisweatherapp.model.WeatherData

class WeatherDetailsFragment : Fragment() {
    private var nameOfCity: String? = null
    private var _binding: WeatherDetailsFragmentBinding? = null
    private val binding: WeatherDetailsFragmentBinding? get() = _binding

    companion object {
        const val KEY = "weather"

        fun instanceOfCity(
            weatherList: WeatherData,
            current_city: String?
        ): WeatherDetailsFragment {
            val fragment = WeatherDetailsFragment()
            val bundle = Bundle()


            bundle.putParcelable(KEY, weatherList)
            fragment.arguments = bundle
            fragment.nameOfCity = current_city

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val weatherList: WeatherData? = arguments?.getParcelable(KEY)
        _binding = WeatherDetailsFragmentBinding.inflate(layoutInflater)

        binding?.tvWeatherInfo?.text = weatherList?.weatherInfo()
        binding?.tvWeatherDetails?.text = weatherList?.weatherMoreInfo()
        binding?.tvWeatherFeels?.text = weatherList?.weatherFeels()
        binding?.tvTemperature?.text = weatherList?.temperature()
        binding?.tvNameOfCity?.text = this.nameOfCity

        binding?.vwInfo?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}