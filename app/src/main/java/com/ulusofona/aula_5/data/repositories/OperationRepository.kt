package com.ulusofona.aula_5.data.repositories

import android.content.Context
import android.util.Log
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.data.remote.services.OperationService
import com.ulusofona.aula_5.data.local.room.dao.OperationDao
import com.ulusofona.aula_5.ui.listeners.OnHistoryChanged
import com.ulusofona.aula_5.ui.utils.Utilities
import com.ulusofona.aula_5.ui.viewmodels.user
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class OperationRepository(private val local: OperationDao, private val remote: Retrofit) : OnHistoryChanged {

    private var listener: OnHistoryChanged? = null
    override fun onStorageChanged(value: List<Operation>?) {
        this.listener?.onStorageChanged(value)
    }
    fun registerListener(listener: OnHistoryChanged, context: Context) {
        this.listener = listener
        listener.onStorageChanged(getAll(context))
    }
    fun unregisterListener() {
        this.listener = null
    }

    fun performOperation(operation: Operation, context: Context) {
        if (Utilities.isNetworkAvailable(context)){
            CoroutineScope(Dispatchers.IO).launch {
                var notOnCloud : List<Operation>?
                if (Utilities.isNetworkAvailable(context)){
                    CoroutineScope(Dispatchers.IO).launch{
                        local.insert(operation)
                        notOnCloud=local.getOnDB()
                        val service = remote.create(OperationService::class.java)
                        for(op in notOnCloud!!){
                            Log.i("ME DEIXA FUGIR", "ME DEIXA FUGIR")
                            if (!op.onWS){
                                val response = user?.token?.let { service.postOperation(it, op) }
                                if (response != null) {
                                    if (response.isSuccessful) {
                                        op.onWS = true
                                        updateScreen(getAll(context))
                                    } else {
                                        response.message()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                local.insert(operation)
                updateScreen(getAll(context))
            }
        }

    }

    fun getAll(context: Context): List<Operation>? {
        var historic : List<Operation>? = null
        if (Utilities.isNetworkAvailable(context)){
            CoroutineScope(Dispatchers.IO).launch {
                val service = remote.create(OperationService::class.java)
                val response = user?.token?.let { service.getOperations(it) }
                if(response!= null) {
                    if (response.isSuccessful) {
                        historic = response.body() as List<Operation>
                        local.deleteAll()
                        for (operation in historic!!){
                            operation.onWS = true
                            local.insert(operation)
                        }
                        updateScreen(historic)
                    } else {
                        response.message()
                        updateScreen(local.getAll())
                    }
                }
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
                historic = local.getAll()
                updateScreen(historic)
            }
        }
        return historic
    }

    fun updateScreen(historic: List<Operation>?) {
        CoroutineScope(Dispatchers.Main).launch {
            listener?.onStorageChanged(historic)
            Log.i("Atualizei a lista", "done")
        }
    }

}