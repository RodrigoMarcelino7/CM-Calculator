package com.ulusofona.aula_5.domain.calculator

import com.ulusofona.aula_5.data.local.list.ListStorage
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorLogic(private val storage: ListStorage) : OnHistoryChanged {

    fun insertSymbol(display: String, symbol: String): String {
        return if (display.isEmpty() && symbol == "0") symbol else display + symbol
    }

    fun performOperation(expression: String): Double {
        val expressionBuilder = ExpressionBuilder(expression).build()
        val result = expressionBuilder.evaluate()
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(
                Operation(
                    expression,
                    result
                )
            )
        }
        return result
    }

    fun getAll(): List<Operation>? {
        var historic: List<Operation>? = null
        CoroutineScope(Dispatchers.IO).launch {
            historic = storage.getAll()
        }
        return historic
    }

    fun onLongClick(item: Operation) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.delete(item)
        }
    }

    override fun onStorageChanged(value: List<Operation>?) {
        storage.onStorageChanged(value)
    }

    fun registerListener(listener: OnHistoryChanged) {
        storage.registerListener(listener)
    }

    fun unregisterListener() {
        storage.unregisterListener()
    }
}