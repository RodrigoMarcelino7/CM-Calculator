package com.ulusofona.aula_5.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class LoggedUser (val email: String, val password: String, val token: String?) {
    @PrimaryKey
    var uuid = UUID.randomUUID().toString()
}