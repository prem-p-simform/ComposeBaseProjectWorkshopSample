package com.simform.navigation.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.simform.navigation.core.route
import kotlin.reflect.KClass

/**
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 * Has default animation already specified and can be overriden
 *
 * @param navController the navController for this host
 * @param startDestination the route for the start destination
 * @param modifier The modifier to be applied to the layout.
 * @param contentAlignment The [Alignment] of the [AnimatedContent]
 * @param route the route for the graph
 * @param enterTransition callback to define enter transitions for destination in this host
 * @param exitTransition callback to define exit transitions for destination in this host
 * @param popEnterTransition callback to define popEnter transitions for destination in this host
 * @param popExitTransition callback to define popExit transitions for destination in this host
 * @param builder the builder used to construct the graph
 */
@Composable
inline fun <reified T : Any> NavHost(
    navController: NavHostController,
    startDestination: KClass<T>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        scaleInEnterTransition()
    },
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        scaleOutExitTransition()
    },
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        scaleInPopEnterTransition()
    },
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        scaleOutPopExitTransition()
    },
    noinline builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder,
    )
}
