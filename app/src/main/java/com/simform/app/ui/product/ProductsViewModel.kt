package com.simform.app.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ProductsUiState()
    )

    private var filterPriceMin: Int? = null
    private var filterPriceMax: Int? = null

    init {
        fetchProducts()
    }

    fun onFilterClick() {
        // TODO Complete the filter
    }

    private fun fetchProducts() {
        // TODO Fetch the products using ProductsRepository
    }
}