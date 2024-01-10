package com.example.learndriver.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Question")
data class Question(
    @PrimaryKey val id: String,
    val question: String,
    @Embedded val option: Option,
    @Embedded val image: Image,
    val answer: String,
    val suggest: String?,
    var currentAnswer: String?,
    var positionIndexRandom: Int?,
    val srcImg: Int?
) : Serializable
