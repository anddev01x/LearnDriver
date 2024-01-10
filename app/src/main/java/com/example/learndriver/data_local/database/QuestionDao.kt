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

    @Query("SELECT * FROM Question ORDER BY RANDOM() LIMIT 35")
    fun getRandomQuestions(): List<Question>

    @Query("UPDATE Question SET currentAnswer = NULL WHERE id IN (:questionIds)")
    suspend fun updateCurrentAnswersToNull(questionIds: List<String>): Int

    @Query("UPDATE Question SET currentAnswer = 'a'")
    fun updateCurrentAnswer()

    @Query("SELECT * FROM Question WHERE currentAnswer IS NULL")
    fun getQuestionsWithNullCurrentAnswer(): List<Question>

    @Query(
        "SELECT * FROM Question WHERE id IN ('10017', '10018', '10019', '10020', '10021', " +
                "'10022', '10023', '10024', '10025', '10026', '10027', '10028', '10029', '10030'," +
                " '10033', '10035', '10036', '10037', '10040', '10043', '10045', '10046', '10047', " +
                "'10048', '10049', '10050', '10051', '10052', '10053', '10084', '10091', '10099'," +
                " '10101', '10109', '10112', '10114', '10118', '10119', '10143', '10145', '10147'," +
                " '10150', '10153', '10154', '10161', '10199', '10200', '10210', '10211', '10214'," +
                " '10221', '10227', '10231', '10242', '10245', '10248', '10258', '10260'," +
                " '10261', '10262')"
    )
    suspend fun getSpecificQuestions(): List<Question>

}
