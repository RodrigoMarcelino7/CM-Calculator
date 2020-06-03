package com.ulusofona.aula_5.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ulusofona.aula_5.data.sensors.location.*
import com.ulusofona.aula_5.domain.map.MapLogic

class MapViewModel (application: Application) : AndroidViewModel(application) {

    var mapLogic = MapLogic()

    fun registerListener(onLocationChangedListener: OnLocationChangedListener){
        mapLogic.registerListener(onLocationChangedListener)
    }

    fun unregisterListener(){
        mapLogic.unregisterListener()
    }
}