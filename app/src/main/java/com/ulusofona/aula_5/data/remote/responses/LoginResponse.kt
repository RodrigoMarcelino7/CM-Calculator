package com.ulusofona.aula_5.data.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse (@SerializedName("email") private var email: String, @SerializedName("token") private var token: String){

    fun getEmail(): String{
        return email
    }

    fun getToken(): String{
        return token
    }
}