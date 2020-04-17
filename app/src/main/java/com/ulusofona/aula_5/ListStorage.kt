package com.ulusofona.aula_5

import kotlinx.coroutines.Dispatchers

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()

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

    fun insert(operation: Operation) {
        with(Dispatchers.IO) {
//            Thread.sleep(30000)
            storage.add(operation)
        }
    }

    fun delete(o : Operation){
        storage.remove(o)
    }

    fun getAll(): List<Operation> {
        return storage.toList()
    }
}