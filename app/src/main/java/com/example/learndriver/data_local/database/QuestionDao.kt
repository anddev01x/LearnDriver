package com.example.learndriver.data_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.learndriver.model.Question

@Dao
interface QuestionDao {
    @Insert
    fun insertQuestions(questions: Question)

    @Query("SELECT * FROM Question")
    fun getAllQuestion(): List<Question>

    @Query("SELECT * FROM Question WHERE CAST(id AS INTEGER) BETWEEN :startId AND :endId")
    fun getQuestionsBetweenIds(startId: String, endId: String): List<Question>

    @Query("UPDATE Question SET currentAnswer = :newAnswer WHERE id = :questionId")
    suspend fun updateCurrentAnswer(questionId: String, newAnswer: String)

    @Query("SELECT * FROM Question WHERE id BETWEEN :startId AND :endId")
    fun getQuestionBetweenIds(startId: String, endId: String): List<Question>
}
