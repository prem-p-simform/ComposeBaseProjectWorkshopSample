package com.simform.design.theme.colors

import androidx.compose.ui.graphics.Color

/**
 * Represents a color token that can be used in a App theme.
 *
 * @property light The color to use in light themes.
 * @property dark The color to use in dark themes.
 */
data class AppColorToken(
    val light: Color,
    val dark: Color
)
