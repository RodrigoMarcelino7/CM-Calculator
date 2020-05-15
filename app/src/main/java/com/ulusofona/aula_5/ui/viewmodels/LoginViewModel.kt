package com.ulusofona.aula_5.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ulusofona.aula_5.data.local.User
import com.ulusofona.aula_5.domain.auth.AuthLogic
import com.ulusofona.aula_5.data.remote.RetrofitBuilder
import com.ulusofona.aula_5.data.local.room.CalculatorDatabase
import com.ulusofona.aula_5.ui.listeners.OnLoginSuccessful
import com.ulusofona.aula_5.ui.listeners.OnLoginUser

const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

var user : User? = User("", "")

class LoginViewModel(application: Application) : AndroidViewModel(application), OnLoginUser {

    private val calculatorDatabase = CalculatorDatabase.getInstance(application)
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT), user, calculatorDatabase.userDao())

    fun authenticateUser(email: String, password: String){
        authLogic.authenticateUser(email, password)
    }

    override fun onLoginUser() {
        authLogic.onLoginUser()
    }

    fun logout(){
        authLogic.logout(calculatorDatabase)
    }

    fun registerListener(listener : OnLoginSuccessful){
        authLogic.registerListener(listener)
    }

    fun unregisterListener(){
        authLogic.unregisterListener()
    }


}