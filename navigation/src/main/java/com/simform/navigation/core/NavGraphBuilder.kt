package com.simform.navigation.core

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.navigation.bottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.simform.navigation.animation.scaleInEnterTransition
import com.simform.navigation.animation.scaleInPopEnterTransition
import com.simform.navigation.animation.scaleOutExitTransition
import com.simform.navigation.animation.scaleOutPopExitTransition
import kotlin.reflect.KClass

/**
 * Add the [Composable] to the [NavGraphBuilder]
 *
 * @param deepLinks list of deep links to associate with the destinations
 * @param enterTransition callback to determine the destination's enter transition
 * @param exitTransition callback to determine the destination's exit transition
 * @param popEnterTransition callback to determine the destination's popEnter transition
 * @param popExitTransition callback to determine the destination's popExit transition
 * @param content composable for the destination
 */
inline fun <reified T : Any> NavGraphBuilder.composable(
    deepLinks: List<NavDeepLink> = listOf(),
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
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) {
    val klass = T::class

    NavigationSerializer.registerRoute<T>()

    composable(
        route = klass.route,
        arguments = listOf(dataArg),
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { entry ->
            content(entry)
        }
    )
}

/**
 * Add the [content] [Composable] as bottom sheet content to the [NavGraphBuilder]
 *
 * @param deepLinks list of deep links to associate with the destinations
 * @param content composable for the destination
 */
inline fun <reified T : Any> NavGraphBuilder.bottomSheet(
    deepLinks: List<NavDeepLink> = listOf(),
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) {
    val klass = T::class

    NavigationSerializer.registerRoute<T>()

    bottomSheet(
        route = klass.route,
        arguments = listOf(dataArg),
        deepLinks = deepLinks,
        content = { entry ->
            content(entry)
        }
    )
}

/**
 * Add the [Composable] to the [NavGraphBuilder] that will be hosted within a
 * [androidx.compose.ui.window.Dialog]. This is suitable only when this dialog represents
 * a separate screen in your app that needs its own lifecycle and saved state, independent
 * of any other destination in your navigation graph. For use cases such as `AlertDialog`,
 * you should use those APIs directly in the [composable] destination that wants to show that
 * dialog.
 *
 * @param deepLinks list of deep links to associate with the destinations
 * @param dialogProperties properties that should be passed to [androidx.compose.ui.window.Dialog].
 * @param content composable content for the destination that will be hosted within the Dialog
 */
inline fun <reified T : Any> NavGraphBuilder.dialog(
    deepLinks: List<NavDeepLink> = listOf(),
    dialogProperties: DialogProperties = DialogProperties(),
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) {
    val klass = T::class

    NavigationSerializer.registerRoute<T>()

    dialog(
        route = klass.route,
        arguments = listOf(dataArg),
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = { entry ->
            content(entry)
        }
    )
}

/**
 * Construct a nested [NavGraph]
 *
 * @param startDestination the starting destination's route for this NavGraph
 * @param route the destination's unique route
 * @param deepLinks list of deep links to associate with the destinations
 * @param enterTransition callback to define enter transitions for destination in this NavGraph
 * @param exitTransition callback to define exit transitions for destination in this NavGraph
 * @param popEnterTransition callback to define pop enter transitions for destination in this
 * NavGraph
 * @param popExitTransition callback to define pop exit transitions for destination in this NavGraph
 * @param builder the builder used to construct the graph
 *
 * @return the newly constructed nested NavGraph
 */
inline fun <reified Route : Any, reified D : Any> NavGraphBuilder.navigation(
    startDestination: KClass<D>,
    route: KClass<Route>,
    deepLinks: List<NavDeepLink> = listOf(),
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
    NavigationSerializer.registerRoute<Route>()

    navigation(
        startDestination = startDestination.route,
        route = route.route,
        arguments = listOf(dataArg),
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder,
    )
}
