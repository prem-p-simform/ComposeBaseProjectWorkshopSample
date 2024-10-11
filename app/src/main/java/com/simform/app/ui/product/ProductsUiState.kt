package com.simform.app.ui.product

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simform.app.domain.model.Product

/**
 * The UI state for [ProductsScreen]
 *
 * @param isLoading True if it's loading
 * @param products The products list
 */
@Stable
@Immutable
data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList()
)
