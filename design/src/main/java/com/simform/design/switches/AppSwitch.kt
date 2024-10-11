package com.simform.design.switches

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.R
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * A base switch composable.
 *
 * @param modifier The modifier to be applied to the switch.
 * @param checked Whether the switch is checked.
 * @param enabled Whether the switch is enabled.
 * @param colors The colors used to style the switch.
 * @param onCheckedChange Called when the user clicks the switch.
 * @param thumbContent The content of the thumb.
 * @param interactionSource The interaction source to be used by the switch.
 */
@Composable
private fun AppBaseSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
    onCheckedChange: ((Boolean) -> Unit)?,
    thumbContent: (@Composable () -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Switch(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        colors = colors,
        thumbContent = thumbContent,
        onCheckedChange = onCheckedChange,
        interactionSource = interactionSource
    )
}

/**
 * A composable that displays a switch.
 *
 * @param modifier The modifier to be applied to the switch.
 * @param enabled Whether the switch is enabled.
 * @param checked Whether the switch is checked.
 * @param onChange Called when the user clicks the switch.
 */
@Composable
fun AppSwitch(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    checked: Boolean,
    onChange: (Boolean) -> Unit
) {
    AppBaseSwitch(
        modifier = modifier,
        enabled = enabled,
        checked = checked,
        onCheckedChange = onChange,
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = AppTheme.appColorScheme.uncheckedSwitchBorderColor,
            uncheckedTrackColor = AppTheme.appColorScheme.uncheckedSwitchBackgroundColor,
            checkedBorderColor = AppTheme.appColorScheme.checkedSwitchBorderColor,
            checkedTrackColor = AppTheme.appColorScheme.checkedSwitchBackgroundColor,
            disabledUncheckedTrackColor = AppTheme.appColorScheme.uncheckedSwitchBackgroundColor,
            disabledCheckedTrackColor = AppTheme.appColorScheme.checkedSwitchBackgroundColor,
        ),
        thumbContent = {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.switch_thumb_size))
                    .clip(CircleShape)
                    .background(
                        color = AppTheme.appColorScheme.switchThumbColor
                    )
            )
        },
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppSwitchPreview() {
    AppPreviewTheme {
        Column {
            AppSwitch(checked = true, onChange = {})
            AppSwitch(checked = false, onChange = {})
        }
    }
}

// endregion
