package com.simform.design.text

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simform.design.theme.typography.LocalTextStyle

/**
 * A base text composable.
 *
 * @param text The text to be displayed.
 * @param modifier The modifier need to be applied to composable.
 * @param color The color of text.
 * @param fontStyle The font style to be applied to the text.
 * @param fontWeight The font weight to be applied to the text.
 * @param fontFamily The font family to be applied to the text.
 * @param letterSpacing The letter spacing to be applied to the text.
 * @param textDecoration The text decoration to be applied to the text.
 * @param textAlign The alignment of the text.
 * @param lineHeight The line height of the text.
 * @param overflow The overflow of the text.
 * @param softWrap True to allow soft wrapping, false otherwise.
 * @param maxLines The maximum number of lines for the text to span, or Int.MAX_VALUE for no limit.
 * @param minLines The minimum number of lines for the text to span.
 * @param onTextLayout Callback that is executed when the text layout is complete.
 * @param style The style of the text.
 */
@Composable
private fun AppBaseText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = style.fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

/**
 * A base text composable.
 *
 * @param text The annotated text to be displayed.
 * @param modifier The modifier need to be applied to composable.
 * @param color The color of text.
 * @param fontStyle The font style to be applied to the text.
 * @param fontWeight The font weight to be applied to the text.
 * @param fontFamily The font family to be applied to the text.
 * @param letterSpacing The letter spacing to be applied to the text.
 * @param textDecoration The text decoration to be applied to the text.
 * @param textAlign The alignment of the text.
 * @param lineHeight The line height of the text.
 * @param overflow The overflow of the text.
 * @param softWrap True to allow soft wrapping, false otherwise.
 * @param maxLines The maximum number of lines for the text to span, or Int.MAX_VALUE for no limit.
 * @param minLines The minimum number of lines for the text to span.
 * @param onTextLayout Callback that is executed when the text layout is complete.
 * @param style The style of the text.
 */
@Composable
private fun AppBaseText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = style.fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        inlineContent = inlineContent,
        onTextLayout = onTextLayout,
        style = style
    )
}

/**
 * A composable that displays text.
 *
 * @param modifier The modifier need to be applied to composable.
 * @param text The text to be displayed.
 * @param textColor The color of text.
 * @param textAlign The alignment of the text.
 * @param overflow The overflow of the text.
 * @param softWrap True to allow soft wrapping, false otherwise.
 * @param maxLines The maximum number of lines for the text to span, or Int.MAX_VALUE for no limit.
 * @param textDecoration The text decoration to be applied to the text.
 * @param style The style of the text.
 * @param onTextLayout Callback that is executed when the text layout is complete.
 */
@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = AppTheme.appColorScheme.textColor,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textDecoration: TextDecoration? = null,
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    AppBaseText(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        textDecoration = textDecoration,
        style = style,
        onTextLayout = onTextLayout
    )
}

/**
 * A composable that displays text.
 *
 * @param modifier The modifier need to be applied to composable.
 * @param text The annotated text to be displayed.
 * @param textColor The color of text.
 * @param textAlign The alignment of the text.
 * @param overflow The overflow of the text.
 * @param softWrap True to allow soft wrapping, false otherwise.
 * @param maxLines The maximum number of lines for the text to span, or Int.MAX_VALUE for no limit.
 * @param inlineContent The inline content to be displayed.
 * @param style The style of the text.
 * @param onTextLayout Callback that is executed when the text layout is complete.
 */
@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    textColor: Color = AppTheme.appColorScheme.textColor,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    style: TextStyle = LocalTextStyle.current,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    AppBaseText(
        text = text,
        modifier = modifier,
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent,
        style = style,
        onTextLayout = onTextLayout
    )
}

// region Previews

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppBaseTextPreview() {
    AppPreviewTheme {
        AppBaseText(
            modifier = Modifier
                .padding(10.dp),
            text = "Base Text",
            color = AppTheme.appColorScheme.textColor,
            style = AppTheme.appTypography.body1Normal
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextPreview() {
    AppPreviewTheme {
        AppText(
            modifier = Modifier
                .padding(10.dp),
            text = "Text"
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAnnotatedTextPreview() {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
            append("Annotated Text")
        }
    }
    AppPreviewTheme {
        AppText(
            modifier = Modifier
                .padding(10.dp),
            text = annotatedString
        )
    }
}

// endregion
