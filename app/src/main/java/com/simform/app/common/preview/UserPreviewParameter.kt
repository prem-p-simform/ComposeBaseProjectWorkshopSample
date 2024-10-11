package com.simform.app.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.simform.app.domain.model.User

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User>
        get() = sequenceOf(
            User(
                name = User.Name(
                    title = "Mr.",
                    first = "John",
                    last = "Doe"
                ),
                location = User.Location(
                    street = User.Street(number = 123, name = "Main Street"),
                    city = "Springfield",
                    state = "Illinois",
                    country = "USA",
                    coordinates = User.Coordinates(latitude = "39.7817", longitude = "-89.6501"),
                    timezone = User.Timezone(
                        offset = "-06:00",
                        description = "Central Time (US & Canada)"
                    )
                ),
                picture = User.Picture(
                    large = "https://randomuser.me/api/portraits/men/1.jpg",
                    medium = "https://randomuser.me/api/portraits/med/men/1.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg"
                ),
                login = User.Login(uuid = "123e4567-e89b-12d3-a456-426614174000")
            ),
            User(
                name = User.Name(
                    title = "Ms.",
                    first = "Jane",
                    last = "Smith"
                ),
                location = User.Location(
                    street = User.Street(number = 456, name = "Elm Street"),
                    city = "Gotham",
                    state = "New York",
                    country = "USA",
                    coordinates = User.Coordinates(latitude = "40.7128", longitude = "-74.0060"),
                    timezone = User.Timezone(
                        offset = "-05:00",
                        description = "Eastern Time (US & Canada)"
                    )
                ),
                picture = User.Picture(
                    large = "https://randomuser.me/api/portraits/women/1.jpg",
                    medium = "https://randomuser.me/api/portraits/med/women/1.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/women/1.jpg"
                ),
                login = User.Login(uuid = "789e0123-e89b-12d3-a456-426614174001")
            ),
            User(
                name = User.Name(
                    title = "Dr.",
                    first = "Alice",
                    last = "Johnson"
                ),
                location = User.Location(
                    street = User.Street(number = 789, name = "Pine Street"),
                    city = "Metropolis",
                    state = "Kansas",
                    country = "USA",
                    coordinates = User.Coordinates(latitude = "39.1142", longitude = "-94.6275"),
                    timezone = User.Timezone(
                        offset = "-06:00",
                        description = "Central Time (US & Canada)"
                    )
                ),
                picture = User.Picture(
                    large = "https://randomuser.me/api/portraits/women/2.jpg",
                    medium = "https://randomuser.me/api/portraits/med/women/2.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/women/2.jpg"
                ),
                login = User.Login(uuid = "456e7890-e89b-12d3-a456-426614174002")
            )
    )
}
