package com.simform.design.icon

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.simform.design.R
import com.simform.design.theme.AppPreviewTheme

/**
 * A composable that displays icon button.
 *
 * @param modifier The modifier need to be applied to composable.
 * @param painter The painter resource to be displayed.
 * @param contentDescription The description for accessibility services.
 * @param tint The tint color for the icon.
 * @param enabled True if the icon button is enabled.
 * @param onClick The callback when the icon button is clicked.
 */
@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "",
    tint: Color = Color.Unspecified,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val minimumInteractionArea =
        dimensionResource(id = R.dimen.icon_mininum_interaction_area)
    val rippleRadius = dimensionResource(id = R.dimen.icon_ripple_radius)
    Box(
        modifier = modifier
            .widthIn(min = minimumInteractionArea)
            .heightIn(min = minimumInteractionArea)
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = rippleRadius)
            ),
        contentAlignment = Alignment.Center
    ) {
        AppIcon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun IconButtonPreview() {
    AppPreviewTheme {
        AppIconButton(
            painter = painterResource(id = android.R.drawable.ic_delete),
            onClick = {}
        )
    }
}

// endregion
