package com.ulusofona.aula_5.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.data.local.entities.LoggedUser
import com.ulusofona.aula_5.data.local.room.dao.OperationDao
import com.ulusofona.aula_5.data.local.room.dao.LoggedUserDao

@Database(entities = arrayOf(Operation::class, LoggedUser::class), version = 1)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun operatioDao(): OperationDao
    abstract fun userDao(): LoggedUserDao

    companion object {
        private var instance: CalculatorDatabase? = null

        fun getInstance(applicationContext: Context): CalculatorDatabase {
            synchronized(this){
                if(instance == null){
                    instance = Room.databaseBuilder(
                        applicationContext,
                        CalculatorDatabase::class.java,
                        "calculator_db"
                    ).build()
                }
                return instance as CalculatorDatabase
            }
        }
    }

}