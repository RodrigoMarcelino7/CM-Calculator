package com.ulusofona.aula_5.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ulusofona.aula_5.data.local.entities.LoggedUser

@Dao
interface LoggedUserDao {

    @Insert
    suspend fun insert(loggedUser: LoggedUser?)

    @Query("DELETE FROM LoggedUser")
    suspend fun delete()

    @Query("SELECT * FROM LoggedUser LIMIT 1")
    suspend fun getAll() : List<LoggedUser>
}