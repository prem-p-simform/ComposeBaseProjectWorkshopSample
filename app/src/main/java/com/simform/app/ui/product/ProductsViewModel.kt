package com.simform.app.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.app.domain.remote.apiresult.onError
import com.simform.app.domain.remote.apiresult.onException
import com.simform.app.domain.remote.apiresult.onSuccess
import com.simform.app.domain.repository.product.ProductRepository
import com.simform.app.ui.product.navigation.ProductFilter
import com.simform.navigation.Navigator
import com.simform.navigation.getResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val productRepository: ProductRepository
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
        viewModelScope.launch {
            navigator.getResult<ProductFilter.Result>(
                destinationKey = ProductFilter.Result.KEY,
                viewModel = this@ProductsViewModel,
            ) {
                filterPriceMin = this?.priceMin
                filterPriceMax = this?.priceMax
                fetchProductsByPriceRange(filterPriceMin, filterPriceMax)
            }
        }
        navigator.navigate(
            ProductFilter(
                priceMin = filterPriceMin,
                priceMax = filterPriceMax
            )
        )
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            productRepository.getProducts()
                .onSuccess { response ->
                    _uiState.update { it.copy(products = response, isLoading = false) }
                }
                .onError { code, message ->
                    _uiState.update { it.copy(isLoading = false) }
                }
                .onException { exception ->
                    Timber.e(exception)
                    _uiState.update { it.copy(isLoading = false) }
                }
        }
    }

    private fun fetchProductsByPriceRange(priceMin: Int?, priceMax: Int?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            productRepository.getProducts(
                priceMin = priceMin,
                priceMax = priceMax
            ).onSuccess { response ->
                _uiState.update { it.copy(products = response, isLoading = false) }
            }.onError { code, message ->
                _uiState.update { it.copy(isLoading = false) }
            }.onException { exception ->
                Timber.e(exception)
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}