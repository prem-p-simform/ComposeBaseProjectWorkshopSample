package com.simform.app.ui.user.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.simform.app.common.preview.UserPreviewParameterProvider
import com.simform.app.domain.model.User
import com.simform.design.R
import com.simform.design.image.AppImage
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * A user item
 * 
 * @param modifier The [Modifier]
 * @param name The name to be shown
 * @param avatar The avatar image to be shown
 */
@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    avatar: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AppImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            url = avatar,
            placeholder = painterResource(R.drawable.ic_avatar_placeholder)
        )
        AppText(text = name, style = AppTheme.appTypography.h5Normal)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UserItemPreview(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 1) user: User
) {
    AppPreviewTheme {
        UserItem(
            name = user.name.first,
            avatar = user.picture.thumbnail
        )
    }
}
