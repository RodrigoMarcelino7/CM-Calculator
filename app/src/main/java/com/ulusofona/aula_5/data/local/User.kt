package com.ulusofona.aula_5.data.local

import com.ulusofona.aula_5.ui.listeners.OnLoginSuccessful

class User (var email :String, var token :String): OnLoginSuccessful {

    private var listener : OnLoginSuccessful? = null

    fun newUser(email: String, token: String){
        this.email = email
        this.token = token
        loginSuccessful(this)
    }

    override fun loginSuccessful(user: User?) {
        listener?.loginSuccessful(user)
    }

    fun registenerListener(listener : OnLoginSuccessful){
        this.listener = listener
    }
    fun unregisterListener(){
        listener = null
    }

}