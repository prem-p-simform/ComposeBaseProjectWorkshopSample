package com.simform.design.tab

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.util.fastForEachIndexed
import com.simform.design.R
import com.simform.design.text.AppText
import com.simform.design.theme.AppTheme

/**
 * A composable that displays tabs.
 *
 * @param modifier The modifier to be applied to the tab row.
 * @param innerPadding The padding values to be applied to the inner content.
 * @param background The background color of the tab row.
 * @param tabs The list of tab titles.
 * @param selectedTabPosition The index of the selected tab.
 * @param onTabSelected The callback function to be invoked when a tab is selected.
 */
@Composable
fun AppTabRow(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(),
    background: Color = AppTheme.appColorScheme.bgColor,
    tabs: List<String>,
    selectedTabPosition: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabsCount = tabs.count()
    val shape = AppTheme.appShapes.tabIndicator

    TabRow(
        modifier = modifier
            .background(background)
            .padding(vertical = dimensionResource(id = R.dimen.tab_row_vertical_padding))
            .padding(innerPadding),
        selectedTabIndex = selectedTabPosition,
        containerColor = Color.Transparent,
        indicator = { tabPositions ->
            if (selectedTabPosition < tabsCount) {
                AppText(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabPosition])
                        .background(
                            color = AppTheme.appColorScheme.tabContainerColor,
                            shape = shape
                        )
                        .padding(
                            vertical = dimensionResource(id = R.dimen.tab_vertical_padding)
                        ),
                    text = tabs[selectedTabPosition],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textColor = AppTheme.appColorScheme.textColor,
                    textAlign = TextAlign.Center
                )
            }
        },
        divider = {}
    ) {
        tabs.fastForEachIndexed { index, value ->
            AppText(
                modifier = Modifier
                    .clip(shape)
                    .clickable(
                        onClick = {
                            onTabSelected(index)
                        }
                    )
                    .padding(
                        vertical = dimensionResource(id = R.dimen.tab_vertical_padding)
                    ),
                text = value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                textColor = AppTheme.appColorScheme.textColor
            )
        }
    }
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTabRowPreview() {
    AppTheme {
        val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
        var selectedTabPosition by remember { mutableIntStateOf(0) }

        AppTabRow(
            tabs = tabs,
            selectedTabPosition = selectedTabPosition,
            onTabSelected = {
                selectedTabPosition = it
            }
        )
    }
}

// endregion
