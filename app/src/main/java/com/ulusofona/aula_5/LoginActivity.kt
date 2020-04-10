package com.ulusofona.aula_5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_login.*
import org.apache.commons.codec.digest.DigestUtils

var users = hashMapOf<String, User>()
var user :User? = null

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        users["admin"] = User("admin", DigestUtils.sha256Hex("admin") , "admin@admin.pt")
    }

    @OnClick(R.id.button_login)
    fun onClickLogin(view: View) {
        if((users[username.text.toString()]?.password).equals(DigestUtils.sha256Hex(password.text.toString()))){
            user = users[username.text.toString()]
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @OnClick(R.id.button_register)
    fun onClickRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}