package com.ulusofona.aula_5.ui.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.ulusofona.aula_5.domain.calculator.CalculatorLogic
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import com.ulusofona.aula_5.data.remote.RetrofitBuilder
import com.ulusofona.aula_5.data.repositories.OperationRepository
import com.ulusofona.aula_5.data.local.room.CalculatorDatabase

class HistoryViewModel (application: Application) : AndroidViewModel(application) {

    private var calculatorLogic = CalculatorLogic(OperationRepository(CalculatorDatabase.getInstance(application).operatioDao(), RetrofitBuilder.getInstance(ENDPOINT)))

    fun registerListener(listener: OnHistoryChanged, context: Context) {
        calculatorLogic.registerListener(listener, context)
    }

    fun unregisterListener() {
        calculatorLogic.unregisterListener()
    }

    fun onLongClick(context: Context){
        calculatorLogic.onLongClick(context)
    }
}