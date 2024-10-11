package com.simform.app.ui.user.detail

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.R
import com.simform.design.image.AppAvatarImage
import com.simform.design.image.AppImage
import com.simform.design.scaffold.AppScaffold
import com.simform.design.textfield.AppOutlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme
import com.simform.app.common.preview.UserPreviewParameterProvider
import com.simform.app.domain.model.User

/**
 * User detail route to show user details.
 * 
 * @param modifier The [Modifier]
 * @param viewModel The [UserDetailViewModel] instance
 */
@Composable
fun UserDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler {
        viewModel.onBackClick()
    }

    UserDetailScreen(
        modifier = modifier,
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
    )
}

/**
 * Standalone screen to show user details.
 * 
 * @param modifier The [Modifier]
 * @param uiState The [UserDetailUiState] instance
 * @param onNameChange The callback when name is changed
 */
@Composable
private fun UserDetailScreen(
    modifier: Modifier = Modifier,
    uiState: UserDetailUiState,
    onNameChange: (String) -> Unit,
) {
    val user = remember(uiState.user) { uiState.user }

    AppScaffold(
        modifier = modifier
    ) { innerPadding ->
        if (user != null) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                val (backgroundImageRef, avatarRef, nameRef) = createRefs()
                AppImage(
                    modifier = Modifier
                        .constrainAs(backgroundImageRef) {
                            width = Dimension.matchParent
                            height = Dimension.value(200.dp)
                        },
                    url = "",
                    placeholder = painterResource(R.drawable.ic_image_placeholder)
                )
                AppAvatarImage(
                    modifier = Modifier
                        .constrainAs(avatarRef) {
                            width = Dimension.value(80.dp)
                            height = Dimension.value(80.dp)
                            linkTo(
                                start = parent.start,
                                top = backgroundImageRef.bottom,
                                end = parent.end,
                                bottom = backgroundImageRef.bottom,
                                startMargin = 16.dp,
                                horizontalBias = 0f,
                            )
                        }
                        .clip(CircleShape),
                    url = user.picture.large,
                    placeholder = painterResource(id = R.drawable.ic_avatar_placeholder)
                )
                AppOutlinedTextField(
                    modifier = Modifier
                        .constrainAs(nameRef) {
                            linkTo(
                                start = avatarRef.end,
                                top = backgroundImageRef.bottom,
                                bottom = parent.bottom,
                                end = parent.end,
                                startMargin = 0.dp,
                                verticalBias = 0f,
                            )
                        },
                    value = uiState.name,
                    onValueChange = onNameChange,
                    shape = AppTheme.appShapes.outlinedTextField,
                    textStyle = AppTheme.appTypography.h5SemiBold,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        focusedTextColor = AppTheme.appColorScheme.textColor,
                        unfocusedTextColor = AppTheme.appColorScheme.textColor,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UserDetailRoutePreview(
    @PreviewParameter(UserPreviewParameterProvider::class, limit = 1) user: User
) {
    AppPreviewTheme {
        UserDetailScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = UserDetailUiState(user = user, name = "Hello"),
            onNameChange = {},
        )
    }
}
