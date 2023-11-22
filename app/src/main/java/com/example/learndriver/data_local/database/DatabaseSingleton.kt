package com.example.learndriver.data_local.database

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: createDatabase(context).also { instance = it }
        }
    }

    private fun createDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Learn Driver Database"
        ).build()
    }
}
