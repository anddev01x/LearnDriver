package com.example.learndriver.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Image(
    @PrimaryKey val imageId: Long,
    @SerializedName("img1") val image1: String?,
    @SerializedName("img2") val image2: String?,
    @SerializedName("img3") val image3: String?,
    @SerializedName("img4") val image4: String?
)
