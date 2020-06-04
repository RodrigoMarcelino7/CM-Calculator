package com.ulusofona.aula_5.ui.utils

import android.app.Application
import com.ulusofona.aula_5.data.sensors.light.Light
import com.ulusofona.aula_5.data.sensors.location.FusedLocation

class CalculatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FusedLocation.start(this)
        Light.start(this)
    }

}