package com.example.learndriver.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.learndriver.model.Question

@Dao
interface QuestionDao {
    @Insert
    fun insertQuestions(questions: List<Question>)
}