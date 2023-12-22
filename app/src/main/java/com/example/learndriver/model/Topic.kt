package com.example.learndriver.model

import java.io.Serializable

data class Topic(
    var imgSrc: Int,
    var topic: String,
    var totalNumber: String,
    var rateProgressbar: Int,
    var currentNumber: String,
) : Serializable
