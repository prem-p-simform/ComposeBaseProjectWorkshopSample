package com.simform.design.segment

import android.content.res.Configuration
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.text.AppText
import com.simform.design.theme.AppTheme

/**
 * A composable that displays segmented buttons.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param enabled Whether the button is enabled or not.
 * @param shape The shape of the button.
 * @param activeTextColor The active text color.
 * @param inActiveTextColor The inactive text color.
 * @param buttonColors The button colors.
 * @param buttons The list of buttons to be displayed.
 * @param selectedButtonPosition The selected button position.
 * @param onButtonSelected The callback when a button is selected.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSegmentedButtons(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: CornerBasedShape = AppTheme.appShapes.segmentedButton,
    activeTextColor: Color = AppTheme.appColorScheme.white,
    inActiveTextColor: Color = AppTheme.appColorScheme.textColor,
    buttonColors: SegmentedButtonColors = SegmentedButtonDefaults.colors(
        activeContainerColor = AppTheme.appColorScheme.primary,
        inactiveContainerColor = AppTheme.appColorScheme.bgColor,
        disabledActiveContainerColor = AppTheme.appColorScheme.primary,
        disabledInactiveContainerColor = AppTheme.appColorScheme.bgColor,
        activeBorderColor = AppTheme.appColorScheme.primary,
        inactiveBorderColor = AppTheme.appColorScheme.primary,
        disabledActiveBorderColor = AppTheme.appColorScheme.primary,
        disabledInactiveBorderColor = AppTheme.appColorScheme.primary,
    ),
    buttons: List<String>,
    selectedButtonPosition: Int,
    onButtonSelected: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        buttons.forEachIndexed { index, label ->
            val isSelected = index == selectedButtonPosition
            SegmentedButton(
                enabled = enabled,
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = buttons.size,
                    baseShape = shape
                ),
                onClick = { onButtonSelected(index) },
                selected = isSelected,
                colors = buttonColors,
                icon = {}
            ) {
                AppText(
                    text = label,
                    textColor = if (isSelected) activeTextColor else inActiveTextColor,
                    style = if (isSelected) {
                        AppTheme.appTypography.body1Normal
                    } else {
                        AppTheme.appTypography.body1Normal
                    }
                )
            }
        }
    }
}

// region Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppSegmentedButtonsPreview() {
    AppTheme {
        val buttons = listOf("Button 1", "Button 2")
        var selectedTabPosition by remember { mutableIntStateOf(0) }

        AppSegmentedButtons(
            buttons = buttons,
            selectedButtonPosition = selectedTabPosition,
            onButtonSelected = {
                selectedTabPosition = it
            }
        )
    }
}

// endregion
