package com.example.learndriver.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Option")
data class Option(
    @PrimaryKey val optionId: Long,
    val a: String,
    val b: String,
    val c: String?,
    val d: String?
)
