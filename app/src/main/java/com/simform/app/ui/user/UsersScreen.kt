package com.simform.app.ui.user

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.design.progress.AppFullScreenProgressIndicator
import com.simform.design.scaffold.AppScaffold
import com.simform.design.theme.AppPreviewTheme
import com.simform.app.domain.model.User
import com.simform.app.ui.user.components.UserItem
import timber.log.Timber

/**
 * Users route to show users.
 * 
 * @param modifier The [Modifier]
 * @param viewModel The [UsersViewModel] instance
 */
@Composable
fun UsersRoute(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    UsersScreen(
        modifier = modifier,
        uiState = uiState,
        onUserClick = viewModel::onUserClick
    )
}

/**
 * Standalone screen to show users.
 * 
 * @param modifier The [Modifier]
 * @param uiState The [UsersUiState] instance
 * @param onUserClick The callback when user is clicked
 */
@Composable
private fun UsersScreen(
    modifier: Modifier = Modifier,
    uiState: UsersUiState,
    onUserClick: (User) -> Unit
) {
    AppScaffold(modifier = modifier) { innerPadding ->
        if (uiState.isLoading) {
            AppFullScreenProgressIndicator()
        } else {
            UsersMainContent(
                modifier = Modifier
                    .padding(innerPadding),
                uiState = uiState,
                onUserClick = onUserClick
            )
        }
    }
}

/**
 * Shows main content of the users screen.
 * 
 * @param modifier The [Modifier]
 * @param uiState The [UsersUiState] instance
 * @param onUserClick The callback when user is clicked
 */
@Composable
private fun UsersMainContent(
    modifier: Modifier = Modifier,
    uiState: UsersUiState,
    onUserClick: (User) -> Unit,
) {
    val users = remember(uiState.users) { uiState.users }

    LazyColumn(
        modifier = modifier
    ) {
        items(users) { user ->
            UserItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            Timber.d("onUserClick inside UI")
                            onUserClick(user)
                        }
                    )
                    .padding(16.dp),
                name = user.name.first + " " + user.name.last,
                avatar = user.picture.large
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UsersScreenPreview() {
    AppPreviewTheme {
        UsersScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = UsersUiState(
                users = listOf(
                    User(name = User.Name(first = "Simform"))
                )
            ),
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingUsersScreenPreview() {
    AppPreviewTheme {
        UsersScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = UsersUiState(isLoading = true),
            onUserClick = {}
        )
    }
}
