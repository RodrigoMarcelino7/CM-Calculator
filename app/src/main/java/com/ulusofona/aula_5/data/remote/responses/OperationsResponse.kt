package com.ulusofona.aula_5.data.remote.responses

import com.google.gson.annotations.SerializedName

data class OperationsResponse (
    @SerializedName("uuid") private var uuid: String,
    @SerializedName("expression") private var expression: String,
    @SerializedName("result") private var result: Double
){

    fun getUuid(): String{
        return uuid
    }

    fun getExpression(): String{
        return expression
    }

    fun getResult(): Double{
        return result
    }
}