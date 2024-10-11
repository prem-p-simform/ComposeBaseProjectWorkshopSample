package com.simform.design.image

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.simform.design.R
import com.simform.design.theme.AppPreviewTheme

/**
 * A base image composable.
 *
 * @param model The model to load.
 * @param contentDescription The content description for accessibility.
 * @param modifier The modifier to apply to this element.
 * @param placeholder The placeholder painter to display while the image is loading.
 * @param error The error painter to display if the image request failed.
 * @param fallback The fallback painter to display if the image request failed and the placeholder painter is null.
 * @param onLoading The callback to invoke when the image is loading.
 * @param onSuccess The callback to invoke when the image is loaded successfully.
 * @param onError The callback to invoke when the image request failed.
 * @param alignment The alignment of the image within its bounds.
 * @param contentScale The content scale to apply to the image.
 * @param alpha The alpha value to apply to the image.
 * @param colorFilter The color filter to apply to the image.
 * @param filterQuality The filter quality to apply to the image.
 */
@Composable
@NonRestartableComposable
private fun AppBaseImage(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = error,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        modifier = modifier,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = onError,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality
    )
}

/**
 * A composable that displays an image from URL.
 *
 * @param modifier The modifier to apply to this element.
 * @param url The URL of the image to load.
 * @param contentScale The content scale to apply to the image.
 * @param placeholder The placeholder painter to display while the image is loading.
 * @param error The error painter to display if the image request failed.
 * @param fallback The fallback painter to display if the image request failed and the placeholder painter is null.
 * @param alignment The alignment of the image within its bounds.
 * @param alpha The alpha value to apply to the image.
 * @param colorFilter The color filter to apply to the image.
 * @param filterQuality The filter quality to apply to the image.
 */
@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter,
    error: Painter = placeholder,
    fallback: Painter = placeholder,
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
) {
    AppBaseImage(
        modifier = modifier,
        model = url,
        contentDescription = "",
        contentScale = contentScale,
        placeholder = placeholder,
        error = error,
        fallback = fallback,
        alignment = alignment,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
    )
}

/**
 * A composable that displays avatar image from URL.
 *
 * @param modifier The modifier to apply to this element.
 * @param url The URL of the image to load.
 * @param placeholder The placeholder painter to display while the image is loading.
 * @param shape The shape of the avatar image.
 * @param onClick The callback to invoke when the avatar image is clicked.
 */
@Composable
fun AppAvatarImage(
    modifier: Modifier = Modifier,
    url: String,
    placeholder: Painter,
    shape: Shape = CircleShape,
    onClick: (() -> Unit)? = null
) {
    AppImage(
        modifier = modifier
            .then(
                if (onClick != null) {
                    Modifier
                        .clip(shape)
                        .clickable(onClick = onClick)
                } else {
                    Modifier
                        .clip(shape)
                }
            ),
        url = url,
        placeholder = placeholder
    )
}

// region Preview

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppImagePreview() {
    AppPreviewTheme {
        AppImage(
            modifier = Modifier
                .size(60.dp),
            url = "",
            placeholder = painterResource(R.drawable.ic_image_placeholder)
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppAvatarImagePreview() {
    AppPreviewTheme {
        AppAvatarImage(
            modifier = Modifier
                .size(60.dp),
            url = "",
            placeholder = painterResource(R.drawable.ic_avatar_placeholder)
        )
    }
}

// endregion
