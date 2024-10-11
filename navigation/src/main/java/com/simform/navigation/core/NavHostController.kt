package com.simform.navigation.core

import android.annotation.SuppressLint
import androidx.core.net.toUri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.navOptions
import timber.log.Timber

/**
 * Navigate to the specified [route]
 *
 * @param route Destination navigate to
 * @param navOptions special options for this navigation operation
 * @param navigatorExtras extras to pass to the Navigator
 */
@SuppressLint("RestrictedApi")
inline fun <reified T: Any> NavController.navigate(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val data = route.parseData()
    val uri = NavDestination.createRoute(data).toUri()

    try {
        val currentRoute = currentBackStackEntry?.destination?.route
        if (currentRoute != route.route) {
            navigate(
                request = NavDeepLinkRequest.Builder.fromUri(uri).build(),
                navOptions = navOptions,
                navigatorExtras = navigatorExtras,
            )
        }
    } catch (e: IllegalArgumentException) {
        // When the data is too large it usually throws IllegalArgumentException "Navigation destination that matches request cannot be found"
        // So we're printing the error instead
        Timber.tag("SaferComposeNavigation").e(e.message.toString())
    } catch (e: IllegalStateException) {
        // This case happens on configuration changes and leads to app crash
        // TODO: Look for better option to handle this case and check if this is stable
        Timber.tag("SaferComposeNavigation").e(e.message.toString())
    }
}

/**
 * Navigate to the specified [route]
 *
 * @param builder The [NavOptionsBuilder] builder
 */
inline fun <reified T: Any> NavController.navigate(
    route: T,
    noinline builder: NavOptionsBuilder.() -> Unit,
) {
    navigate(
        route = route::class.route,
        navOptions = navOptions(builder),
    )
}

/**
 * Navigate up with a given result [navRoute]
 *
 * @param navRoute The result value
 */
fun NavController.navigationUpWithResult(
    navRoute: Any
) {
    val backStackEntry = getBackStackEntry(navRoute)
    backStackEntry.savedStateHandle["result"] = navRoute.parseData()
    popBackStack(navRoute)
}

/**
 * Pops the backstack to a specific destination [navRoute]
 *
 * @param navRoute The destination popup to
 * @param inclusive Whether the given destination should also be popped.
 * @param saveState Whether the back stack and the state of all destinations between the
 * current destination and the [route] should be saved for later
 * restoration via [NavOptions.Builder.setRestoreState] or the `restoreState` attribute using
 * the same [route] (note: this matching ID is true whether
 * [inclusive] is true or false).
 */
fun NavController.popBackStack(
    navRoute: Any,
    inclusive: Boolean = false,
    saveState: Boolean = false
) {
    popBackStack(navRoute.route, inclusive, saveState)
}

/**
 * Gets the topmost [NavBackStackEntry] for the given route.
 *
 * @param navRoute Route of the destination which exists in the backstack.
 */
fun NavController.getBackStackEntry(navRoute: Any): NavBackStackEntry =
    getBackStackEntry(navRoute.route)
