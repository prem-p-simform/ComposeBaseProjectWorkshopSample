package com.simform.design.theme

import android.app.Activity
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.simform.design.surface.AppSurface
import com.simform.design.theme.colors.AppColorScheme
import com.simform.design.theme.colors.LocalAppColorScheme
import com.simform.design.theme.colors.LocalAppContentColor
import com.simform.design.theme.colors.colorScheme
import com.simform.design.theme.gradients.AppGradientColorScheme
import com.simform.design.theme.gradients.LocalAppGradientColorScheme
import com.simform.design.theme.gradients.gradientColorScheme
import com.simform.design.theme.shapes.AppShapes
import com.simform.design.theme.shapes.LocalAppShapes
import com.simform.design.theme.typography.AppTypography
import com.simform.design.theme.typography.LocalAppTypography
import com.simform.design.theme.typography.LocalTextStyle

/**
 * Represents the theme of the application.
 *
 * @param darkTheme True if the theme should be dark, false otherwise.
 * @param appShapes The shapes to use in the application.
 * @param appTypography The typography to use in the application.
 * @param content The content of the theme.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    appShapes: AppShapes = AppTheme.appShapes,
    appTypography: AppTypography = AppTheme.appTypography,
    content: @Composable () -> Unit
) {
    val colorScheme = colorScheme(darkTheme)
    val gradientColorScheme = gradientColorScheme(darkTheme)
    val contentColor = colorScheme.bgColor
    val rippleIndication = ripple(color = colorScheme.ripple)

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.statusBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppContentColor provides contentColor,
        LocalAppGradientColorScheme provides gradientColorScheme,
        LocalIndication provides rippleIndication,
        LocalAppShapes provides appShapes,
        LocalTextStyle provides appTypography.body1Normal,
        content = content
    )
}

@Composable
fun AppPreviewTheme(
    content: @Composable () -> Unit
) {
    AppTheme {
        AppSurface(content = content)
    }
}

object AppTheme {

    /**
     * Retrieves the current [AppColorScheme] at the call site's position in the hierarchy.
     */
    val appColorScheme: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColorScheme.current

    /**
     * Retrieves the current [AppGradientColorScheme] at the call site's position in the hierarchy.
     */
    val appGradientColorScheme: AppGradientColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppGradientColorScheme.current

    /**
     * Retrieves the current [AppShapes] at the call site's position in the hierarchy.
     */
    val appShapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    /**
     * Retrieves the current [AppTypography] at the call site's position in the hierarchy.
     */
    val appTypography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}
