package com.ulusofona.aula_5

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val calculatorLogic = CalculatorLogic()
    var display: String = "0"
    private var listener: OnDisplayChanged? = null

    private fun notifyOnDisplayChanged(){
        listener?.onDisplayChanged(display)
    }

    fun registerListener(listener: OnDisplayChanged){
        this.listener = listener
        listener.onDisplayChanged(display)
    }

    fun unregisterListener(){
        listener = null
    }

    fun onClickSymbol(symbol: String){
        display = if(display != "0") calculatorLogic.insertSymbol(display, symbol) else symbol
        notifyOnDisplayChanged()
    }

    fun onClickEquals(){
        display = calculatorLogic.performOperation(display).toString()
        notifyOnDisplayChanged()
    }

    fun onClickClear(){
        display = "0"
        notifyOnDisplayChanged()
    }

    fun onClickBackSpace(){
        display = if(display.length > 1) display.substring(0,display.length-1) else "0"
        notifyOnDisplayChanged()
    }
}