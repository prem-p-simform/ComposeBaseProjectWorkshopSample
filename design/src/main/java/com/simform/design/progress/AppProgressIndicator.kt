package com.simform.design.progress

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.simform.design.R
import com.simform.design.theme.AppPreviewTheme

/**
 * A composable that displays a progress indicator.
 *
 * @param modifier The modifier to be applied to the progress indicator.
 * @param size The size of the progress indicator.
 */
@Composable
fun AppProgressIndicator(
    modifier: Modifier = Modifier,
    size: Dp = dimensionResource(id = R.dimen.progress_indicator_size),
) {
    CircularProgressIndicator(
        modifier = modifier
            .size(size)
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppProgressIndicatorPreview() {
    AppPreviewTheme {
        AppProgressIndicator()
    }
}

// endregion
