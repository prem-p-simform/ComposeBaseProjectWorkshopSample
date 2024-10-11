package com.simform.app.ui.user

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simform.app.domain.model.User

/**
 * Defines ui state for [UsersScreen]
 * 
 * @param users The list of [User]
 * @param isLoading True is it's in loading state, false otherwise
 */
@Stable
@Immutable
data class UsersUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
)
