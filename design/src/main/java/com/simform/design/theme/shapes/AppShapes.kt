package com.simform.design.theme.shapes

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.staticCompositionLocalOf
import com.simform.design.theme.AppTheme

/**
 * A shape scheme holds all the named shape parameters for a [AppTheme].
 *
 * @property button The shape of the button.
 * @property outlinedTextField The shape of the outlined text field.
 * @property segmentedButton The shape of the segmented button.
 * @property tabIndicator The shape of the tab indicator.
 * @property bottomSheet The bottom sheet shape.
 */
data class AppShapes(
    val button: CornerBasedShape = AppShapeTokens.button,
    val outlinedTextField: CornerBasedShape = AppShapeTokens.outlinedTextField,
    val segmentedButton: CornerBasedShape = AppShapeTokens.segmentedButton,
    val tabIndicator: CornerBasedShape = AppShapeTokens.tabIndicator,
    val bottomSheet: CornerBasedShape = AppShapeTokens.bottomSheet
)

/**
 * CompositionLocal used to pass [AppShapes] down the tree.
 *
 * Setting the value here is typically done as part of [AppTheme].
 * To retrieve the current value of this CompositionLocal, use
 * [AppTheme.appShapes].
 */
internal val LocalAppShapes = staticCompositionLocalOf { AppShapes() }

