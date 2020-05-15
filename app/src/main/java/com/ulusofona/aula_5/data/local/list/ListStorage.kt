package com.ulusofona.aula_5.data.local.list

import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class ListStorage private constructor() : OnHistoryChanged {

    private val storage = mutableListOf<Operation>()

    private var listener: OnHistoryChanged? = null

    companion object {
        private var instance: ListStorage? = null
        fun getInstance(): ListStorage {
            synchronized(this) {
                if (instance == null) {
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    suspend fun insert(operation: Operation) {
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            storage.add(operation)
        }
        withContext(Dispatchers.Main){
            onStorageChanged(storage)
        }
    }

    suspend fun delete(o: Operation) {
        withContext(Dispatchers.IO){
            Thread.sleep(1000)
            storage.remove(o)
        }
        withContext(Dispatchers.Main){
            onStorageChanged(storage)
        }
    }

    suspend fun getAll(): List<Operation>? {
        var storageList: List<Operation>? = null
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
            storageList = storage.toList()
        }
        return storageList
    }

    fun unregisterListener() {
        listener = null
    }

    fun registerListener(listener: OnHistoryChanged) {
        this.listener = listener
        listener.onStorageChanged(storage)
    }

    override fun onStorageChanged(value: List<Operation>?) {
        listener?.onStorageChanged(storage)
    }
}