package com.example.learndriver.model

import com.google.gson.annotations.SerializedName

data class ResponseListQuestion(
    @SerializedName("question")
    val question: List<Question>
)
