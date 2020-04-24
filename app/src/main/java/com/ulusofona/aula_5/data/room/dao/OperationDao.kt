package com.ulusofona.aula_5.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ulusofona.aula_5.data.local.entities.Operation

@Dao
interface OperationDao {

    @Insert
    suspend fun insert(operation: Operation)

    @Query("SELECT * FROM operation")
    suspend fun gatAll() : List<Operation>
}