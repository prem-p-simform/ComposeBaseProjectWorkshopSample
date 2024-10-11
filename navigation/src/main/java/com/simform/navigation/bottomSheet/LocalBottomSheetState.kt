package com.simform.navigation.bottomSheet

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * Provides an [BottomSheetState] object to a composable.
 *
 * This state provides current state of the bottom sheet.
 */
val LocalBottomSheetState: ProvidableCompositionLocal<BottomSheetState> = compositionLocalOf {
    error("No AppBottomSheetHiddenState provided")
}

/**
 * Represents the state of the bottom sheet.
 *
 * @property state The state of the bottom sheet.
 * @property isHidden Whether the bottom sheet is hidden.
 * @property isVisible Whether the bottom sheet is visible.
 */
data class BottomSheetState(
    private val state: ModalBottomSheetState,
) {
    val isHidden: Boolean
        get() = !state.isVisible
    val isVisible: Boolean
        get() = state.isVisible
}