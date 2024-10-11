package com.simform.design.appbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.R
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.appColorScheme.bgColor,
    leadingContent: @Composable () -> Unit = {},
    centerContent: @Composable () -> Unit = {},
    trailingContent: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.app_bar_padding)
            ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor
        ),
        title = centerContent,
        navigationIcon = leadingContent,
        actions = trailingContent
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTopAppBarPreview() {
    AppPreviewTheme {
        AppTopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            centerContent = {
                AppText(
                    text = "App Bar"
                )
            }
        )
    }
}

// endregion
