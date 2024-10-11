package com.simform.navigation

import androidx.compose.animation.core.AnimationSpec
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ModalBottomSheetValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.navigation.BottomSheetNavigator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.simform.navigation.bottomSheet.BottomSheetState

/**
 * Represents a navigation controller
 *
 * @param navController The [NavHostController] instance
 * @param bottomSheetNavigator The [BottomSheetNavigator] instance
 * @param appBottomSheetState The [BottomSheetState] instance
 */
@Stable
@Immutable
data class NavigatorController(
    val navController: NavHostController,
    val bottomSheetNavigator: BottomSheetNavigator,
    val appBottomSheetState: BottomSheetState
)

/**
 * Creates a [NavigatorController]
 *
 * @param navigator The [Navigator] instance
 * @param bottomSheetAnimationSpec The animation spec for bottom sheet
 * @param skipHalfExpanded Skip the half expanded state of the bottom sheet
 *
 * @return The [NavigatorController]
 */
@Composable
fun rememberNavigatorController(
    navigator: Navigator,
    bottomSheetAnimationSpec: AnimationSpec<Float> = ModalBottomSheetDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = true,
): NavigatorController {
    val bottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = bottomSheetAnimationSpec,
        skipHalfExpanded = skipHalfExpanded
    )
    val bottomSheetNavigator: BottomSheetNavigator = remember(bottomSheetState) {
        BottomSheetNavigator(bottomSheetState)
    }
    val navController: NavHostController = rememberNavController(bottomSheetNavigator)
    val appBottomSheetState = remember(bottomSheetState) {
        BottomSheetState(bottomSheetState)
    }

    NavigatorEffect(navController = navController, navigator = navigator)

    return remember(navController, bottomSheetNavigator, appBottomSheetState) {
        NavigatorController(
            navController = navController,
            bottomSheetNavigator = bottomSheetNavigator,
            appBottomSheetState = appBottomSheetState
        )
    }
}
