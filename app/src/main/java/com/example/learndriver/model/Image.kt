package com.example.learndriver.model

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "Image",
    indices = [Index("image1", unique = true), Index("image2"), Index("image3"), Index("image4")]
)
data class Image(
    @SerializedName("img1") val image1: String?,
    @SerializedName("img2") val image2: String?,
    @SerializedName("img3") val image3: String?,
    @SerializedName("img4") val image4: String?
)
