package com.simform.app.ui.product.filter

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * The UI state for [ProductFilterScreen]
 *
 * @param priceMin The minimum price of the product
 * @param priceMax The maximum price of the product
 */
@Stable
@Immutable
data class ProductFilterUiState(
    val priceMin: Int? = null,
    val priceMax: Int? = null
)