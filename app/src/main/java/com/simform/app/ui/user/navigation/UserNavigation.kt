package com.simform.app.ui.user.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.simform.app.ui.user.UsersRoute
import com.simform.app.ui.user.detail.UserDetailRoute
import com.simform.navigation.core.composable
import com.simform.navigation.core.navigation

/**
 * The user nav graph.
 */
fun NavGraphBuilder.userNavigation() {
    navigation(
        route = UsersRoute::class,
        startDestination = Users::class
    ) {
        composable<Users> {
            UsersRoute(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
            )
        }

        composable<UserDetails> {
            UserDetailRoute(
                modifier = Modifier
                    .systemBarsPadding()
                    .fillMaxSize()
            )
        }
    }
}