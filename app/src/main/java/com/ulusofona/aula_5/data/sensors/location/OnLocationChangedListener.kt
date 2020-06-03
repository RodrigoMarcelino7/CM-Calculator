package com.ulusofona.aula_5.data.sensors.location

import com.google.android.gms.location.LocationResult

interface OnLocationChangedListener {

    fun onLocationChanged(locationResult : LocationResult)

}