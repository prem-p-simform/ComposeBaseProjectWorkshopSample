package com.simform.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * The navigator effect attaches the [navController] with [Navigator].
 *
 * @param navController The [NavHostController] to attach
 * @param navigator The [Navigator] to attach with
 */
@Composable
fun NavigatorEffect(navController: NavHostController, navigator: Navigator) {
    val scope = rememberCoroutineScope { Dispatchers.Main }
    DisposableEffect(navController) {
        scope.launch {
            navigator.attachController(controller = navController)
        }
        onDispose {
            scope.launch {
                navigator.disposeController()
            }
        }
    }
}