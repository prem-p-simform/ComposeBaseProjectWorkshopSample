package com.simform.design.theme.colors

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.simform.design.theme.AppTheme

/**
 * A color scheme holds all the named color parameters for a [AppTheme].
 *
 * @property primary The primary color.
 * @property secondary The secondary color.
 * @property textColor The text color.
 * @property bgColor The background color.
 * @property white The white color.
 * @property textFieldContainerColor The text field container color.
 * @property textFieldBorderColor The text field border color.
 * @property error The error color.
 * @property success The success color.
 * @property switchThumbColor The switch thumb color.
 * @property checkedSwitchBorderColor The checked switch border color.
 * @property uncheckedSwitchBorderColor The unchecked switch border color.
 * @property checkedSwitchBackgroundColor The checked switch background color.
 * @property uncheckedSwitchBackgroundColor The unchecked switch background color.
 * @property tabContainerColor The tab container color.
 * @property bottomSheetScrim The bottom sheet scrim color.
 */
data class AppColorScheme(
    val primary: Color,
    val secondary: Color,
    val textColor: Color,
    val bgColor: Color,
    val white: Color,
    val textFieldContainerColor: Color,
    val textFieldBorderColor: Color,
    val error: Color,
    val success: Color,
    val switchThumbColor: Color,
    val checkedSwitchBorderColor: Color,
    val uncheckedSwitchBorderColor: Color,
    val checkedSwitchBackgroundColor: Color,
    val uncheckedSwitchBackgroundColor: Color,
    val tabContainerColor: Color,
    val ripple: Color,
    val bottomSheetScrim: Color,
    val statusBarColor: Color,
)

fun colorScheme(darkTheme: Boolean): AppColorScheme = with(darkTheme) {
    AppColorScheme(
        primary = color(AppColorTokens.primary),
        secondary = color(AppColorTokens.secondary),
        textColor = color(AppColorTokens.textColor),
        bgColor = color(AppColorTokens.bgColor),
        white = color(AppColorTokens.white),
        textFieldContainerColor = color(AppColorTokens.textFieldContainerColor),
        textFieldBorderColor = color(AppColorTokens.textFieldBorderColor),
        error = color(AppColorTokens.error),
        success = color(AppColorTokens.success),
        switchThumbColor = color(AppColorTokens.switchThumbColor),
        checkedSwitchBorderColor = color(AppColorTokens.checkedSwitchBorderColor),
        uncheckedSwitchBorderColor = color(AppColorTokens.uncheckedSwitchBorderColor),
        checkedSwitchBackgroundColor = color(AppColorTokens.checkedSwitchBackgroundColor),
        uncheckedSwitchBackgroundColor = color(AppColorTokens.uncheckedSwitchBackgroundColor),
        tabContainerColor = color(AppColorTokens.tabContainerColor),
        ripple = color(AppColorTokens.ripple),
        bottomSheetScrim = color(AppColorTokens.bottomSheetScrim),
        statusBarColor = color(AppColorTokens.statusBarColor)
    )
}

/**
 * Chooses a [Color] based on [Boolean]. If it's true chooses [AppColorToken]
 *
 * @receiver Boolean value to choose from
 * @param colorToken The [AppColorToken] to choose from
 */
private fun Boolean.color(colorToken: AppColorToken): Color =
    if (this) colorToken.dark else colorToken.light

/**
 * CompositionLocal used to pass [AppColorScheme] down the tree.
 *
 * Setting the value here is typically done as part of [AppTheme].
 * To retrieve the current value of this CompositionLocal, use
 * [AppTheme.appColorScheme].
 */
internal val LocalAppColorScheme = staticCompositionLocalOf { colorScheme(false) }

