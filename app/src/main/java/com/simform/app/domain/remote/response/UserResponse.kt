package com.simform.app.domain.remote.response

import com.simform.app.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("results")
    val results: List<User>,

    @SerialName("info")
    val info: Info
) {
    @Serializable
    data class Info(
        @SerialName("seed")
        val seed: String,

        @SerialName("results")
        val results: Int,

        @SerialName("page")
        val page: Int,

        @SerialName("version")
        val version: String
    )
}
