package com.simform.design.textfield

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simform.design.theme.typography.LocalTextStyle

/**
 * A base outlined text field composable.
 *
 * @param value The current value of the text field.
 * @param onValueChange Called when the value changes.
 * @param modifier Modifier to be applied to the text field.
 * @param enabled Controls the enabled state of the text field.
 * @param readOnly If true, the text field is read-only.
 * @param textStyle The text style to be applied to the text field.
 * @param label The label to be displayed in the text field.
 * @param placeholder The placeholder text to be displayed in the text field.
 * @param leadingIcon The leading icon to be displayed in the text field.
 * @param trailingIcon The trailing icon to be displayed in the text field.
 * @param prefix The prefix text to be displayed in the text field.
 * @param suffix The suffix text to be displayed in the text field.
 * @param supportingText The supporting text to be displayed in the text field.
 * @param isError If true, the text field is in an error state.
 * @param visualTransformation The visual transformation to be applied to the text field.
 * @param keyboardOptions The keyboard options to be applied to the text field.
 * @param keyboardActions The keyboard actions to be applied to the text field.
 * @param singleLine If true, the text field is single-line.
 * @param maxLines The maximum number of lines to be displayed in the text field.
 * @param minLines The minimum number of lines to be displayed in the text field.
 * @param interactionSource The interaction source to be used by the text field.
 * @param shape The shape to be applied to the text field.
 * @param colors The colors to be used by the text field.
 */
@Composable
private fun AppBaseOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        shape = shape,
        colors = colors
    )
}

/**
 * A composable that displays outlined text field.
 *
 * @param modifier Modifier to be applied to the text field.
 * @param value The current value of the text field.
 * @param shape The shape to be applied to the text field.
 * @param enabled Controls the enabled state of the text field.
 * @param readOnly If true, the text field is read-only.
 * @param singleLine If true, the text field is single-line.
 * @param minLines The minimum number of lines to be displayed in the text field.
 * @param maxLines The maximum number of lines to be displayed in the text field.
 * @param textStyle The text style to be applied to the text field.
 * @param keyboardOptions The keyboard options to be applied to the text field.
 * @param colors The colors to be used by the text field.
 * @param leadingIcon The leading icon to be displayed in the text field.
 * @param trailingIcon The trailing icon to be displayed in the text field.
 * @param placeholder The placeholder text to be displayed in the text field.
 * @param supportingText The supporting text to be displayed in the text field.
 * @param onValueChange Called when the value changes.
 */
@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    shape: Shape = AppTheme.appShapes.outlinedTextField,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppTheme.appColorScheme.textColor,
        unfocusedTextColor = AppTheme.appColorScheme.textColor,
        disabledTextColor = AppTheme.appColorScheme.textColor,
        focusedContainerColor = AppTheme.appColorScheme.textFieldContainerColor,
        unfocusedContainerColor = AppTheme.appColorScheme.textFieldContainerColor,
        disabledContainerColor = AppTheme.appColorScheme.textFieldContainerColor,
        focusedBorderColor = if (readOnly) {
            AppTheme.appColorScheme.textFieldContainerColor
        } else {
            AppTheme.appColorScheme.textFieldBorderColor
        },
        unfocusedBorderColor = AppTheme.appColorScheme.textFieldBorderColor,
        disabledBorderColor = AppTheme.appColorScheme.textFieldBorderColor,
        focusedSupportingTextColor = AppTheme.appColorScheme.textColor,
        unfocusedSupportingTextColor = AppTheme.appColorScheme.textColor,
        disabledSupportingTextColor = AppTheme.appColorScheme.textColor,
    ),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    supportingText: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    AppBaseOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        minLines = minLines,
        maxLines = maxLines,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        placeholder = placeholder,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        shape = shape,
        supportingText = supportingText,
        colors = colors
    )
}

// region Previews

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppOutlinedTextFieldPreview() {
    AppPreviewTheme {
        AppOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            value = "Outlined Text Field",
            shape = AppTheme.appShapes.outlinedTextField,
            textStyle = AppTheme.appTypography.body1Normal,
            placeholder = {},
            onValueChange = {}
        )
    }
}

// endregion
