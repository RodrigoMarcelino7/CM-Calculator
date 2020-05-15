package com.ulusofona.aula_5.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.ulusofona.aula_5.R
import com.ulusofona.aula_5.data.local.User
import com.ulusofona.aula_5.ui.listeners.OnLoginSuccessful
import com.ulusofona.aula_5.ui.listeners.OnLoginUser
import com.ulusofona.aula_5.ui.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

const val EXTRA_EMAIL = "com.example.acalculator.ui.activities.EMAIL"
const val EXTRA_TOKEN = "com.example.acalculator.ui.activities.TOKEN"

class LoginActivity : AppCompatActivity(), OnLoginSuccessful, OnLoginUser {

    private lateinit var loginViewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.onLoginUser()
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        loginViewModel.registerListener(this)
        super.onStart()
    }

    override fun onDestroy() {
        loginViewModel.unregisterListener()
        super.onDestroy()
    }

    @OnClick(R.id.button_login)
    fun onClickLogin() {
        loginViewModel.authenticateUser(email.text.toString(), password.text.toString())
    }

    @OnClick(R.id.button_register)
    fun onClickRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun loginSuccessful(user: User?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onLoginUser() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}