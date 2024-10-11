package com.simform.app.ui.product.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.simform.app.ui.product.ProductsRoute
import com.simform.app.ui.product.filter.ProductFilterRoute
import com.simform.navigation.core.composable
import com.simform.navigation.core.navigation

/**
 * Product navigation graph.
 */
fun NavGraphBuilder.productNavigation() {
    navigation(
        startDestination = Products::class,
        route = ProductRoute::class,
    ) {
        composable<Products> {
            ProductsRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            )
        }

        composable<ProductFilter> {
            ProductFilterRoute(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            )
        }
    }
}