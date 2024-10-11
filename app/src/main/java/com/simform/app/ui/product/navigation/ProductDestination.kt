package com.simform.app.ui.product.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data object ProductRoute

@Serializable
data object Products

@Serializable
data class ProductFilter(
    @SerialName("priceMin")
    val priceMin: Int?,

    @SerialName("priceMax")
    val priceMax: Int?
) {
    @Serializable
    data class Result(
        @SerialName("priceMin")
        val priceMin: Int?,

        @SerialName("priceMax")
        val priceMax: Int?
    ) {
        companion object {
            const val KEY = "PRODUCT_FILTER_RESULT"
        }
    }
}