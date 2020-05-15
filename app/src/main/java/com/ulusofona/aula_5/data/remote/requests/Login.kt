package com.ulusofona.aula_5.data.remote.requests

import com.google.gson.annotations.SerializedName

data class Login (@SerializedName("email") private val email: String, @SerializedName("password") private val password: String){
}