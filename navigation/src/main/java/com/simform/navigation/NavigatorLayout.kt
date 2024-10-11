package com.simform.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.contentColorFor
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.simform.navigation.animation.NavHost
import com.simform.navigation.animation.scaleInEnterTransition
import com.simform.navigation.animation.scaleInPopEnterTransition
import com.simform.navigation.animation.scaleOutExitTransition
import com.simform.navigation.animation.scaleOutPopExitTransition
import com.simform.navigation.bottomSheet.LocalBottomSheetState
import kotlin.reflect.KClass

/**
 * A navigator layout is convenient wrapper to easily setup navigation.
 *
 * @param startDestination The start destination of the graph
 * @param navigatorController The [NavigatorController]. Create using [rememberNavigatorController].
 * @param enterTransition callback to define enter transitions for destination in this host
 * @param exitTransition callback to define exit transitions for destination in this host
 * @param popEnterTransition callback to define popEnter transitions for destination in this host
 * @param popExitTransition callback to define popExit transitions for destination in this host
 */
@Composable
inline fun <reified T : Any> NavigatorLayout(
    startDestination: KClass<T>,
    navigatorController: NavigatorController,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
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
    noinline builder: NavGraphBuilder.() -> Unit
) {
    ModalBottomSheetLayout(
        bottomSheetNavigator = navigatorController.bottomSheetNavigator,
        modifier = Modifier,
        sheetShape = sheetShape,
        sheetElevation = sheetElevation,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        scrimColor = scrimColor,
    ) {
        CompositionLocalProvider(
            LocalBottomSheetState provides navigatorController.appBottomSheetState
        ) {
            NavHost<T>(
                navController = navigatorController.navController,
                startDestination = startDestination,
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition,
                builder = builder
            )
        }
    }
}