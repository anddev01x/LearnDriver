package com.example.learndriver.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.learndriver.model.Question

@Database(entities = [Question::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}