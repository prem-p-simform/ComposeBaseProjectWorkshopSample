package com.simform.app.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * A screen title.
 *
 * @param modifier The [Modifier]
 */
@Composable
fun ScreenTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    AppText(
        modifier = modifier,
        text = title,
        style = AppTheme.appTypography.h2Bold,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenTitlePreview() {
    AppPreviewTheme {
        ScreenTitle(
            title = "TITLE"
        )
    }
}
