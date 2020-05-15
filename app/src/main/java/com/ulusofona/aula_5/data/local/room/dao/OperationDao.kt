package com.ulusofona.aula_5.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ulusofona.aula_5.data.local.entities.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insert(operation: Operation)

    @Query("DELETE FROM Operation")
    suspend fun deleteAll()

    @Query("SELECT * FROM operation")
    suspend fun getAll() : List<Operation>?

    @Query("SELECT * FROM operation where onWS = '0'")
    suspend fun getOnDB() : List<Operation>?

}