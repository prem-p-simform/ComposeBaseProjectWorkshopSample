package com.simform.design.theme.gradients

import androidx.compose.ui.graphics.Color

/**
 * Represents a gradient token that can be used in a App theme.
 *
 * @property light The gradient to use in light themes.
 * @property dark The gradient to use in dark themes.
 */
data class AppGradientToken(
    val light: List<Color>,
    val dark: List<Color>
)
