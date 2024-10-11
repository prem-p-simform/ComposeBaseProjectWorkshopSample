package com.simform.design.button

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simform.design.R
import com.simform.design.icon.AppIcon
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simform.design.theme.typography.LocalTextStyle

/**
 * A base button composable.
 *
 * @param modifier The modifier need to be applied to composable.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param shape The shape of the button.
 * @param colors The colors to use for the button in different states.
 * @param elevation The elevation of the button.
 * @param border  The border to use for the button.
 * @param contentPadding The padding to use for the button's content.
 * @param interactionSource The MutableInteractionSource representing the stream of Interactions for this Button.
 * @param onClick The callback when the button is clicked.
 * @param content The content of the button.
 */
@Composable
private fun AppBaseButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * A base outlined button composable.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param shape The shape of the button.
 * @param colors The colors to use for the button in different states.
 * @param elevation The elevation of the button.
 * @param border The border to use for the button.
 * @param contentPadding The padding to use for the button's content.
 * @param interactionSource The MutableInteractionSource representing the stream of Interactions for this Button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param content The content of the button.
 */
@Composable
private fun AppBaseOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}

/**
 * A composable that displays text button.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param backgroundColor The background color to use for the button.
 * @param shape The shape of the button.
 * @param contentPadding The padding to use for the button's content.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param content The content of the button.
 */
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = AppTheme.appColorScheme.primary,
    disabledBackgroundColor: Color = backgroundColor.copy(alpha = 0.5F),
    shape: Shape = AppTheme.appShapes.button,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    AppBaseButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = disabledBackgroundColor
        ),
        shape = shape,
        contentPadding = contentPadding,
        content = content
    )
}

/**
 * A composable that displays icon button.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param text The text to be displayed inside the button.
 * @param shape The shape of the button.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param textColor The text color to use for the button.
 * @param textStyle The text style to use for the button.
 * @param border The border to use for the button.
 * @param backgroundColor The background color to use for the button.
 * @param contentPadding The padding to use for the button's content.
 * @param leadingIcon The leading icon to be displayed inside the button.
 * @param trailingIcon The trailing icon to be displayed inside the button.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape,
    enabled: Boolean = true,
    textColor: Color = AppTheme.appColorScheme.white,
    textStyle: TextStyle = LocalTextStyle.current,
    border: BorderStroke? = null,
    backgroundColor: Color = AppTheme.appColorScheme.primary,
    contentPadding: PaddingValues = PaddingValues(),
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    AppBaseButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f)
        ),
        enabled = enabled,
        border = border,
        contentPadding = contentPadding
    ) {
        leadingIcon?.invoke()
        AppText(
            text = text,
            style = textStyle,
            textColor = textColor
        )
        trailingIcon?.invoke()
    }
}

/**
 * A composable that displays outlined button.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param text The text to be displayed inside the button.
 * @param textColor The text color to use for the button.
 * @param textStyle The text style to use for the button.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param shape The shape of the button.
 * @param colors The colors to use for the button in different states.
 * @param elevation The elevation of the button.
 * @param border The border to use for the button.
 * @param contentPadding The padding to use for the button's content.
 * @param leadingIcon The leading icon to be displayed inside the button.
 * @param trailingIcon The trailing icon to be displayed inside the button.
 * @param onClick The callback to be invoked when the button is clicked.
 */
@Composable
fun AppOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = AppTheme.appColorScheme.textColor,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    AppBaseOutlinedButton(
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        onClick = onClick
    ) {
        leadingIcon?.invoke()
        AppText(
            text = text,
            style = textStyle,
            textColor = textColor
        )
        trailingIcon?.invoke()
    }
}

/**
 * A composable that displays rounded button.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param enabled Controls the enabled state of the button. When false, the button will not be clickable.
 * @param backgroundColor The background color to use for the button.
 * @param onClick The callback to be invoked when the button is clicked.
 * @param content The content of the button.
 */
@Composable
fun AppRoundedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = AppTheme.appColorScheme.primary,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    AppBaseButton(
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        content = content
    )
}

// region Previews

@Preview(showBackground = true, group = "TEXT BUTTON")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, group = "TEXT BUTTON")
@Composable
private fun AppButtonPreview() {
    AppPreviewTheme {
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentPadding = PaddingValues(vertical = 15.dp),
            onClick = {}
        ) {
            AppText(
                text = "Simple button",
                textColor = AppTheme.appColorScheme.white,
                style = AppTheme.appTypography.body1Normal
            )
        }
    }
}

@Preview(showBackground = true, group = "ICON BUTTON")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, group = "ICON BUTTON")
@Composable
private fun AppIconButtonPreview() {
    AppPreviewTheme {
        AppIconButton(
            modifier = Modifier
                .padding(10.dp),
            text = "Icon",
            textColor = AppTheme.appColorScheme.white,
            shape = AppTheme.appShapes.button,
            leadingIcon = {
                AppIcon(
                    painter = painterResource(id = android.R.drawable.ic_delete),
                    tint = AppTheme.appColorScheme.white
                )
                Spacer(modifier = Modifier.width(10.dp))
            },
            contentPadding = PaddingValues(10.dp),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, group = "OUTLINED BUTTON")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, group = "OUTLINED BUTTON")
@Composable
private fun AppOutlinedButtonPreview() {
    AppPreviewTheme {
        AppOutlinedButton(
            modifier = Modifier
                .padding(10.dp),
            text = "Outlined",
            shape = AppTheme.appShapes.button,
            border = BorderStroke(
                width = 2.dp,
                color = AppTheme.appColorScheme.primary,
            ),
            leadingIcon = {
                AppIcon(
                    painter = painterResource(id = android.R.drawable.ic_delete),
                    tint = AppTheme.appColorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
            },
            onClick = {}
        )
    }
}

@Preview(showBackground = true, group = "ROUNDED BUTTON")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, group = "MORE BUTTON")
@Composable
private fun AppMoreButtonPreview() {
    AppPreviewTheme {
        AppRoundedButton(
            modifier = Modifier
                .padding(10.dp)
                .size(dimensionResource(id = R.dimen.btn_size)),
            backgroundColor = AppTheme.appColorScheme.primary,
            onClick = {}
        ) {
            AppIcon(
                painter = painterResource(id = android.R.drawable.ic_delete),
                tint = AppTheme.appColorScheme.white
            )
        }
    }
}

// endregion
