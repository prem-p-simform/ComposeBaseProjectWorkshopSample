package com.simform.design.icon

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.theme.AppPreviewTheme

/**
 * A composable that displays icon.
 *
 * @param modifier The modifier need to be applied to composable.
 * @param painter The painter resource to be displayed.
 * @param contentDescription The description for accessibility services.
 * @param tint The tint color for the icon.
 */
@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "",
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        tint = tint
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppIconPreview() {
    AppPreviewTheme {
        AppIcon(
            painter = painterResource(id = android.R.drawable.ic_delete)
        )
    }
}
