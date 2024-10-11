package com.simform.app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id")
    val categoryId: Int,

    @SerialName("name")
    val categoryName: String,

    @SerialName("image")
    val categoryImage: String
)