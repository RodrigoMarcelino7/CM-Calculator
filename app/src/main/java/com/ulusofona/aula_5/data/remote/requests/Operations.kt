package com.ulusofona.aula_5.data.remote.requests

import com.google.gson.annotations.SerializedName

data class Operations (@SerializedName("uuid") private val uuid: String,
                       @SerializedName("expression") private val expression: String,
                       @SerializedName("result") private val result: Double
                       ){

}