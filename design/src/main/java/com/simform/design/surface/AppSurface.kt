package com.simform.design.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.simform.design.theme.AppTheme

/**
 * A surface composable.
 *
 * @param modifier The modifier to be applied to the Surface.
 * @param shape The shape of the Surface.
 * @param color The color of the Surface.
 * @param tonalElevation The tonal elevation of the Surface.
 * @param shadowElevation The shadow elevation of the Surface.
 * @param border The border of the Surface.
 * @param content The content of the Surface.
 */
@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = AppTheme.appColorScheme.bgColor,
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
        border = border,
        content = content
    )
}
