package com.simform.app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val productId: Int,

    @SerialName("title")
    val productTitle: String,

    @SerialName("price")
    val productPrice: Int,

    @SerialName("description")
    val productDescription: String,

    @SerialName("category")
    val productCategory: Category,

    @SerialName("images")
    val productImages: List<String>
)
