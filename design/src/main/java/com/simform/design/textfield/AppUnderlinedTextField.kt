package com.simform.design.textfield

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * A base underlined text field composable.
 *
 * @param value The current text field value.
 * @param onValueChange Called when the value changes.
 * @param modifier Modifier to be applied to the text field.
 * @param enabled Whether the text field is enabled or not.
 * @param readOnly Whether the text field is read-only or not.
 * @param textStyle The text style to be applied to the text field.
 * @param label The label to be displayed in the text field.
 * @param placeholder The placeholder text to be displayed in the text field.
 * @param leadingIcon The leading icon to be displayed in the text field.
 * @param trailingIcon The trailing icon to be displayed in the text field.
 * @param prefix The prefix text to be displayed in the text field.
 * @param suffix The suffix text to be displayed in the text field.
 * @param supportingText The supporting text to be displayed in the text field.
 * @param isError Whether the text field is in error state or not.
 * @param visualTransformation The visual transformation to be applied to the text field.
 * @param keyboardOptions The keyboard options to be applied to the text field.
 * @param keyboardActions The keyboard actions to be applied to the text field.
 * @param singleLine Whether the text field should be treated as a single line.
 * @param maxLines The maximum number of lines to be displayed in the text field.
 * @param minLines The minimum number of lines to be displayed in the text field.
 * @param interactionSource The interaction source to be used by the text field.
 * @param shape The shape of the text field.
 * @param contentPadding The content padding to be applied to the text field.
 * @param colors The text field colors to be used.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBaseUnderlinedTextField(
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
    shape: Shape = TextFieldDefaults.shape,
    contentPadding: PaddingValues = PaddingValues(),
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = AppTheme.appColorScheme.primary,
        unfocusedIndicatorColor = Color.Transparent
    )
) {
    BasicTextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        cursorBrush = SolidColor(textStyle.color),
        maxLines = maxLines,
        minLines = minLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                prefix = prefix,
                suffix = suffix,
                supportingText = supportingText,
                shape = shape,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = contentPadding
            )
        }
    )
}

/**
 * A composable that displays underlined text field.
 *
 * @param modifier Modifier to be applied to the text field.
 * @param value The current text field value.
 * @param enabled Whether the text field is enabled or not.
 * @param readOnly Whether the text field is read-only or not.
 * @param singleLine Whether the text field should be treated as a single line.
 * @param minLines The minimum number of lines to be displayed in the text field.
 * @param maxLines The maximum number of lines to be displayed in the text field.
 * @param isError Whether the text field is in error state or not.
 * @param textStyle The text style to be applied to the text field.
 * @param keyboardOptions The keyboard options to be applied to the text field.
 * @param visualTransformation The visual transformation to be applied to the text field.
 * @param colors The text field colors to be used.
 * @param label The label to be displayed in the text field.
 * @param placeholder The placeholder text to be displayed in the text field.
 * @param leadingIcon The leading icon to be displayed in the text field.
 * @param trailingIcon The trailing icon to be displayed in the text field.
 * @param prefix The prefix text to be displayed in the text field.
 * @param suffix The suffix text to be displayed in the text field.
 * @param supportingText The supporting text to be displayed in the text field.
 * @param isError Whether the text field is in error state or not.
 * @param contentPadding The content padding to be applied to the text field.
 * @param onValueChange Called when the value changes.
 */
@Composable
fun AppUnderlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    isError: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = AppTheme.appColorScheme.primary,
        unfocusedIndicatorColor = AppTheme.appColorScheme.primary
    ),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(),
    onValueChange: (String) -> Unit
) {
    AppBaseUnderlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        minLines = minLines,
        maxLines = maxLines,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        colors = colors,
        contentPadding = contentPadding
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppUnderlinedInputFieldPreview() {
    AppPreviewTheme {
        AppUnderlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            value = "",
            textStyle = AppTheme.appTypography.body1Normal,
            placeholder = {
                AppText(
                    text = "Underlined Text Field",
                    textColor = AppTheme.appColorScheme.textColor,
                )
            },
            onValueChange = { },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words
            ),
            contentPadding = PaddingValues(vertical = 10.dp)
        )
    }
}

// endregion
