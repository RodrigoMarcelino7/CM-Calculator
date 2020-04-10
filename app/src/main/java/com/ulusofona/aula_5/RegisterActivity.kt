package com.ulusofona.aula_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.username
import org.apache.commons.codec.digest.DigestUtils

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    @OnClick(R.id.button_register)
    fun onClickConfirm(view: View){
        if(users[username.text.toString()] == null && password.text.toString() == password_confirm.text.toString()){
            users[username.text.toString()] = User(username.text.toString(), DigestUtils.sha256Hex(password.text.toString()), email.text.toString())
            user = users[username.text.toString()]
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
