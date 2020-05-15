package com.ulusofona.aula_5.domain.auth

import android.util.Log
import com.ulusofona.aula_5.data.local.User
import com.ulusofona.aula_5.data.local.entities.LoggedUser
import com.ulusofona.aula_5.data.remote.requests.Login
import com.ulusofona.aula_5.data.remote.services.AuthService
import com.ulusofona.aula_5.data.local.room.CalculatorDatabase
import com.ulusofona.aula_5.data.local.room.dao.LoggedUserDao
import com.ulusofona.aula_5.ui.listeners.OnLoginSuccessful
import com.ulusofona.aula_5.ui.listeners.OnLoginUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AuthLogic (private val retrofit: Retrofit, private val user: User?, private val loggedUserDao: LoggedUserDao) : OnLoginSuccessful, OnLoginUser {

    fun authenticateUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = retrofit.create(AuthService::class.java)
            val response = service.login(Login(email, password))
            if(response.isSuccessful){
                val token = response.body()?.getToken()
                token?.let{user?.newUser(email,it)}
                loggedUserDao.insert(LoggedUser(email, password, token))
            }
            else {
                Log.i("Erro", response.message())
            }
        }
    }

    fun logout(calculatorDatabase: CalculatorDatabase){
        CoroutineScope(Dispatchers.IO).launch {
            calculatorDatabase.userDao().delete()
        }
    }

    override fun loginSuccessful(user: User?) {
        user?.loginSuccessful(user)
    }

    fun registerListener(listener : OnLoginSuccessful){
        user?.registenerListener(listener)
    }
    fun unregisterListener(){
        user?.unregisterListener()
    }

    override fun onLoginUser() {
        CoroutineScope(Dispatchers.IO).launch {
            if(loggedUserDao.getAll().isNotEmpty()){
                val users = loggedUserDao.getAll()
                authenticateUser(users[0].email, users[0].password)
            }
        }
    }


}