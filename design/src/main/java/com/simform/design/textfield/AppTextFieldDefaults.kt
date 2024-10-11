package com.simform.design.textfield

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.simform.design.text.AppText
import com.simform.design.theme.AppTheme

object AppTextFieldDefaults {
    /**
     * A composable to show an error message.
     *
     * @param modifier The modifier to apply to composable.
     * @param message The error message to show.
     */
    @Composable
    fun Error(
        modifier: Modifier = Modifier,
        message: String
    ) {
        AppText(
            modifier = modifier,
            text = message,
            textColor = AppTheme.appColorScheme.error
        )
    }

    /**
     * A composable to show an success message.
     *
     * @param modifier The modifier to apply to composable.
     * @param message The success message to show.
     */
    @Composable
    fun Success(
        modifier: Modifier = Modifier,
        message: String
    ) {
        AppText(
            modifier = modifier,
            text = message,
            textColor = AppTheme.appColorScheme.success
        )
    }
}
