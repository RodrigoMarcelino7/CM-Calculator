package com.ulusofona.aula_5.domain.calculator

import android.content.Context
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.data.repositories.OperationRepository
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic(private val repository: OperationRepository) : OnHistoryChanged{

    fun insertSymbol(display: String, symbol: String): String {
        return if (display.isEmpty() && symbol == "0") symbol else display + symbol
    }

    fun performOperation(expression: String, context: Context): Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        repository.performOperation(Operation(expression,result, false), context)
        return result
    }

    fun getAll(context: Context) : List<Operation>? {
        return repository.getAll(context)
    }

    fun onLongClick(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            //repository.deleteOperation(context)
        }
    }

    override fun onStorageChanged(value: List<Operation>?) {
        repository.onStorageChanged(value)
    }

    fun registerListener(listener: OnHistoryChanged, context: Context) {
        repository.registerListener(listener, context)
    }
    fun unregisterListener() {
        repository.unregisterListener()
    }
}