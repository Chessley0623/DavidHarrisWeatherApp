package com.example.davidharrisweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.davidharrisweatherapp.view.HomeFragment
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatButton = this.findViewById(R.id.floating_button)
        supportFragmentManager.beginTransaction().replace(R.id.v_container, HomeFragment()).commit()

        floatButton!!.setOnClickListener {
            val weatherType = floatButton!!.text.toString()

            floatButton!!.text = when (weatherType) {
                "°F" -> "°C"
                "°C" -> "°K"
                else -> "°F"
            }
        }
    }

    companion object {
        var floatButton: ExtendedFloatingActionButton? = null
    }
}