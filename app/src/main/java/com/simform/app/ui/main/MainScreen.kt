package com.simform.app.ui.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.simform.design.surface.AppSurface
import com.simform.design.theme.AppTheme
import com.simform.navigation.rememberNavigatorController

/**
 * The main screen. The root composable of our application.
 */
@Composable
fun MainScreen() {
    AppTheme {
        val mainViewModel: MainViewModel = hiltViewModel()
        val navigatorController = rememberNavigatorController(mainViewModel.navigator)

        AppSurface {
//            NavigatorLayout(
//                startDestination = OnboardingRoute::class,
//                navigatorController = navigatorController,
//                sheetBackgroundColor = AppTheme.appColorScheme.bgColor,
//                scrimColor = AppTheme.appColorScheme.bgColor.copy(alpha = 0.85F)
//            ) {
//                onboardingNavigation()
//                productNavigation()
//            }
        }
    }
}
