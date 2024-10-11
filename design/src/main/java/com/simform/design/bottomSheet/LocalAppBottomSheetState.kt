package com.simform.design.bottomSheet

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * Provides an LocalAppBottomSheetState object to a composable.
 */
val LocalAppBottomSheetState: ProvidableCompositionLocal<AppBottomSheetState> = compositionLocalOf {
    error("No LocalAppBottomSheetHiddenState provided")
}

/**
 * Represents the state of the bottom sheet.
 *
 * @property state The state of the bottom sheet.
 * @property isHidden Whether the bottom sheet is hidden.
 * @property isVisible Whether the bottom sheet is visible.
 */
data class AppBottomSheetState(
    private val state: ModalBottomSheetState,
) {
    val isHidden: Boolean
        get() = !state.isVisible
    val isVisible: Boolean
        get() = state.isVisible
}