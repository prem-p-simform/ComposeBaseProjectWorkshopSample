package com.simform.app.ui.user.detail

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.simform.app.domain.model.User

/**
 * Defines ui state for [UserDetailScreen]
 * 
 * @param user The [User], null if not loaded yet
 * @param name The name of the user
 */
@Stable
@Immutable
data class UserDetailUiState(
    val user: User? = null,
    val name: String = if (user == null) "" else "${user.name.first} ${user.name.last}",
)
