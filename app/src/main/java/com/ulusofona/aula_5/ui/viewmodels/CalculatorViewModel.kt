package com.ulusofona.aula_5.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ulusofona.aula_5.data.local.list.ListStorage
import com.ulusofona.aula_5.domain.calculator.CalculatorLogic
import com.ulusofona.aula_5.ui.listeners.OnDisplayChanged
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged

class CalculatorViewModel (application: Application) : AndroidViewModel(application) {

    private val storage = ListStorage.getInstance()

    private val calculatorLogic = CalculatorLogic(storage)

    private val history = calculatorLogic.getAll()

    var display: String = "0"

    private var displayListener: OnDisplayChanged? = null

    private var historyListener: OnHistoryChanged? = null

    private fun notifyOnDisplayChanged() {
        displayListener?.onDisplayChanged(display)
    }

    fun registerListener(displayListener: OnDisplayChanged/*, historyListener: OnHistoryChanged*/) {
        this.displayListener = displayListener
        //this.historyListener = historyListener
        displayListener.onDisplayChanged(display)
        //historyListener.onStorageChanged(history)
    }

    fun unregisterListener() {
        displayListener = null
    }

    fun onClickSymbol(symbol: String) {
        display = if (display != "0") calculatorLogic.insertSymbol(display, symbol) else symbol
        notifyOnDisplayChanged()
    }

    fun onClickEquals() {
        display = calculatorLogic.performOperation(display).toString()
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