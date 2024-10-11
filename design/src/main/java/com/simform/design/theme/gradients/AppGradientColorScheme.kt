package com.simform.design.theme.gradients

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.simform.design.theme.AppTheme
import com.simform.design.theme.colors.AppColorToken

/**
 * A gradient color scheme holds all the named gradient color parameters for a [AppTheme].
 *
 * @property primaryGradient The primary gradient.
 * @property secondaryGradient The secondary gradient.
 */
data class AppGradientColorScheme(
    val primaryGradient: List<Color>,
    val secondaryGradient: List<Color>
)

fun gradientColorScheme(darkTheme: Boolean): AppGradientColorScheme = with(darkTheme) {
    AppGradientColorScheme(
        primaryGradient = color(AppGradientTokens.primaryGradient),
        secondaryGradient = color(AppGradientTokens.secondaryGradient)
    )
}

/**
 * Chooses a [Color] based on [Boolean]. If it's true chooses [AppColorToken]
 *
 * @receiver Boolean value to choose from
 * @param colorToken The [AppColorToken] to choose from
 */
fun Boolean.color(colorToken: AppGradientToken): List<Color> =
    if (this) colorToken.dark else colorToken.light

/**
 * CompositionLocal used to pass [AppGradientColorScheme] down the tree.
 *
 * Setting the value here is typically done as part of [AppTheme].
 * To retrieve the current value of this CompositionLocal, use
 * [AppTheme.appGradientColorScheme].
 */
internal val LocalAppGradientColorScheme = staticCompositionLocalOf { gradientColorScheme(false) }

