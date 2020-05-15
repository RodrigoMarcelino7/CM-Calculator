package com.ulusofona.aula_5.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.ulusofona.aula_5.data.remote.RetrofitBuilder
import com.ulusofona.aula_5.data.repositories.OperationRepository
import com.ulusofona.aula_5.data.local.room.CalculatorDatabase
import com.ulusofona.aula_5.domain.calculator.CalculatorLogic
import com.ulusofona.aula_5.ui.listeners.OnDisplayChanged
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged

class CalculatorViewModel (application: Application) : AndroidViewModel(application) {

    private val calculatorLogic = CalculatorLogic(OperationRepository(CalculatorDatabase.getInstance(application).operatioDao(), RetrofitBuilder.getInstance(ENDPOINT)))

    var display: String = "0"

    private var displayListener: OnDisplayChanged? = null

    private fun notifyOnDisplayChanged() {
        displayListener?.onDisplayChanged(display)
    }

    fun registerListener(displayListener: OnDisplayChanged, historyListener: OnHistoryChanged, context: Context) {
        this.displayListener = displayListener
        displayListener.onDisplayChanged(display)
        calculatorLogic.registerListener(historyListener,context)
    }

    fun unregisterListener() {
        displayListener = null
        calculatorLogic.unregisterListener()
    }

    fun onClickSymbol(symbol: String) {
        display = if (display != "0") calculatorLogic.insertSymbol(display, symbol) else symbol
        notifyOnDisplayChanged()
    }

    fun onClickEquals(context: Context) {
        display = calculatorLogic.performOperation(display, context).toString()
        notifyOnDisplayChanged()
    }

    fun onClickClear() {
        display = "0"
        notifyOnDisplayChanged()
    }

    fun onClickBackSpace() {
        display = if (display.length > 1) display.substring(0, display.length - 1) else "0"
        notifyOnDisplayChanged()
    }

}