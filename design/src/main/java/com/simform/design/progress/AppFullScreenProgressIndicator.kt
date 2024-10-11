package com.simform.design.progress

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.theme.AppPreviewTheme

/**
 * A composable that displays a full screen progress indicator.
 *
 * @param modifier The modifier to apply to the composable.
 */
@Composable
fun AppFullScreenProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppProgressIndicator()
    }
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppFullScreenProgressIndicatorPreview() {
    AppPreviewTheme {
        AppFullScreenProgressIndicator()
    }
}

// endregion
