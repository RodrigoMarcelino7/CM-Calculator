package com.ulusofona.aula_5.domain.map

import com.google.android.gms.location.LocationResult
import com.ulusofona.aula_5.data.sensors.location.FusedLocation
import com.ulusofona.aula_5.data.sensors.location.OnLocationChangedListener

class MapLogic () {

    fun registerListener(locationListener: OnLocationChangedListener) {
        FusedLocation.registerListener(locationListener)
    }

    fun unregisterListener() {
        FusedLocation.unregisterListener()
    }
}