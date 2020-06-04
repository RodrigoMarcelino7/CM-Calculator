package com.ulusofona.aula_5.data.sensors.light

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import java.util.*

class Light private constructor(context: Context) : SensorEventListener{

    private val TAG = Light::class.java.simpleName

    //Este objeto permite-nos aceder aos vários sensores
    private var sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    companion object{
        private var instance : Light? = null

        fun start(context: Context){
            instance = if(instance == null) Light(context) else instance
            instance?.start()
        }
    }

    //Inicializa a captura de valores por parte do sensor
    private fun start(){
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.i(TAG, "onAccuracyChanged")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.i(TAG, Arrays.toString(event?.values))
    }

}