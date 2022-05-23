package com.example.davidharrisweatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.davidharrisweatherapp.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = HomeFragmentBinding.inflate(layoutInflater)
        binding.lookup.setOnClickListener {

            val userInput = binding.userCity.text.toString()
            val support = activity?.supportFragmentManager?.beginTransaction()

            support?.replace(
                com.example.davidharrisweatherapp.R.id.v_container,
                WeatherResultsFragment(userInput)
            )
                ?.addToBackStack(null)?.commit()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}