package com.simform.design.divider

import android.content.res.Configuration
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.simform.design.theme.AppPreviewTheme

/**
 * A composable that displays a horizontal divider.
 *
 * @param modifier The modifier to be applied to the divider.
 * @param thickness The thickness of the divider.
 * @param color The color of the divider.
 */
@Composable
fun AppHorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.color
) {
    HorizontalDivider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppHorizontalDividerPreview() {
    AppPreviewTheme {
        AppHorizontalDivider()
    }
}

// endregion
