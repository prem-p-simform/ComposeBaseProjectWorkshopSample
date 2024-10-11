package com.simform.app.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User API response.
 */
@Serializable
data class User(
    @SerialName("name")
    val name: Name = Name(),

    @SerialName("location")
    val location: Location = Location(),

    @SerialName("picture")
    val picture: Picture = Picture(),

    @SerialName("login")
    val login: Login = Login()
) {
    @Serializable
    data class Name(
        @SerialName("title")
        val title: String = "",

        @SerialName("first")
        val first: String = "",

        @SerialName("last")
        val last: String = ""
    ) {
        override fun toString(): String = "$title $first $last"
    }

    @Serializable
    data class Location(
        @SerialName("street")
        val street: Street = Street(),

        @SerialName("city")
        val city: String = "",

        @SerialName("state")
        val state: String = "",

        @SerialName("country")
        val country: String = "",

        @SerialName("coordinates")
        val coordinates: Coordinates = Coordinates(),

        @SerialName("timezone")
        val timezone: Timezone = Timezone()
    ) {
        fun address(): String {
            return "$street, $city, $state, $country"
        }
    }

    @Serializable
    data class Coordinates(
        @SerialName("latitude")
        val latitude: String = "",

        @SerialName("longitude")
        val longitude: String = ""
    )

    @Serializable
    data class Timezone(
        @SerialName("offset")
        val offset: String = "",

        @SerialName("description")
        val description: String = ""
    )

    @Serializable
    data class Street(
        @SerialName("number")
        val number: Int = 0,

        @SerialName("name")
        val name: String = ""
    ) {
        override fun toString(): String = "$number, $name"
    }

    @Serializable
    data class Picture(
        @SerialName("large")
        val large: String = "",

        @SerialName("medium")
        val medium: String = "",

        @SerialName("thumbnail")
        val thumbnail: String = ""
    )

    @Serializable
    data class Login(
        @SerialName("uuid")
        val uuid: String = ""
    )
}
