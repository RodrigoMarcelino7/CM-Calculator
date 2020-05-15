package com.ulusofona.aula_5.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Operation(val expression: String, val result: Double, var onWS: Boolean){

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()
}