package com.ulusofona.aula_5

import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {
    var storage = ListStorage.getInstance()
    private var calculatorLogic = CalculatorLogic()

    private var listener: OnHistoryChanged? = null

    private fun notifyOnHistoryChanged() {
        listener?.onStorageChanged()
    }

    fun registerListener(listener: OnHistoryChanged) {
        this.listener = listener
        listener.onStorageChanged()
    }

    fun unregisterListener() {
        listener = null
    }

    fun onClickEquals() {
        notifyOnHistoryChanged()
    }

    fun onLongClick(item: Operation){
        calculatorLogic.onLongClick(item)
        notifyOnHistoryChanged()
    }
}