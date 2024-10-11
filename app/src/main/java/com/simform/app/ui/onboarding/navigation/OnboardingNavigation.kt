package com.simform.app.ui.onboarding.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.simform.app.ui.onboarding.login.LoginRoute
import com.simform.navigation.core.composable
import com.simform.navigation.core.navigation

/**
 * Onboarding navigation graph.
 */
fun NavGraphBuilder.onboardingNavigation() {
    navigation(
        startDestination = Login::class,
        route = OnboardingRoute::class
    ) {
        composable<Login> {
            LoginRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }
    }
}