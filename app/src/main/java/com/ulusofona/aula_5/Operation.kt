package com.ulusofona.aula_5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Operation(val expression: String, val result: Double) : Parcelable{

    var uuid: String = UUID.randomUUID().toString()

}