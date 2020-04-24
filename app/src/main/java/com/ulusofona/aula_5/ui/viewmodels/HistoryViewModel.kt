package com.ulusofona.aula_5.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.ulusofona.aula_5.domain.calculator.CalculatorLogic
import com.ulusofona.aula_5.data.local.list.ListStorage
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import com.ulusofona.aula_5.data.local.entities.Operation

class HistoryViewModel : ViewModel() {
    var storage = ListStorage.getInstance()

    private var calculatorLogic = CalculatorLogic(storage)

    fun registerListener(listener: OnHistoryChanged) {
        calculatorLogic.registerListener(listener)
    }

    fun unregisterListener() {
        calculatorLogic.unregisterListener()
    }

    fun onLongClick(item: Operation){
        calculatorLogic.onLongClick(item)
    }
}