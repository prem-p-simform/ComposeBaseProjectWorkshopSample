package com.simform.app.ui.product.filter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.app.ui.product.navigation.ProductFilter
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductFilterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
): ViewModel() {

    private val navArgs = requireNotNull(savedStateHandle.getNavArgs<ProductFilter>())

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    fun onPriceMinChange(price: Int?) {
        _uiState.update { it.copy(priceMin = price) }
    }

    fun onPriceMaxChange(price: Int?) {
        _uiState.update { it.copy(priceMax = price) }
    }

    fun onFilterClick() {
        // TODO Send filter result back
    }

    fun onResetClick() {
        // TODO Reset filter and send result back
    }

    private fun getDefaultUiState(): ProductFilterUiState = ProductFilterUiState(
        priceMin = navArgs.priceMin,
        priceMax = navArgs.priceMax
    )
}