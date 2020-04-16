package com.ulusofona.aula_5

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_calculator.*

class HistoryViewModel : ViewModel() {
    var storage = ListStorage.getInstance()

    private var listener: OnHistoryChanged? = null

    private fun notifyOnHistoryChanged(){
        listener?.onStorageChanged(storage)
    }

    fun registerListener(listener: OnHistoryChanged){
        this.listener = listener
        listener.onStorageChanged(storage)
    }

    fun unregisterListener(){
        listener = null
    }

    fun onClickEquals(){
        notifyOnHistoryChanged()
    }
}