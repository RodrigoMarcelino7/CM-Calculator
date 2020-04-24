package com.ulusofona.aula_5.data.local.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Operation(val expression: String, val result: Double) : Parcelable{

    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()

}