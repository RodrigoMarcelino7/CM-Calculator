package com.ulusofona.aula_5.ui.listeners

import com.ulusofona.aula_5.data.local.entities.Operation

interface OnHistoryChanged {
    fun onStorageChanged(value:List<Operation>?)
}