package com.simform.app.ui.user.navigation

import com.simform.app.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data object UsersRoute

@Serializable
data object Users

@Serializable
data class UserDetails(
    @SerialName("user")
    val user: User
) {

    @Serializable
    data class Result(
        val name: String,
    ) {

        companion object {
            const val KEY = "USER_DETAILS_RESULT_KEY"
        }
    }
}