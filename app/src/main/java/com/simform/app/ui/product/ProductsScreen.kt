package com.simform.app.ui.product

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.app.R
import com.simform.app.common.previewparameter.ProductsPreviewParameter
import com.simform.app.domain.model.Product
import com.simform.app.ui.product.components.ProductItem
import com.simform.design.appbar.AppTopAppBar
import com.simform.design.icon.AppIconButton
import com.simform.design.progress.AppFullScreenProgressIndicator
import com.simform.design.scaffold.AppScaffold
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * The products route to show list of products.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [ProductsViewModel]
 */
@Composable
fun ProductsRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductsScreen(
        modifier = modifier,
        uiState = uiState,
        onFilterClick = viewModel::onFilterClick
    )
}

/**
 * Standalone screen to show list of products.
 *
 * @param modifier The [Modifier]
 * @param uiState The [ProductsUiState]
 */
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    uiState: ProductsUiState,
    onFilterClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                leadingContent = {
                    AppText(
                        text = stringResource(R.string.title_products),
                        style = AppTheme.appTypography.h6SemiBold
                    )
                },
                trailingContent = {
                    AppIconButton(
                        painter = painterResource(R.drawable.ic_filter),
                        onClick = onFilterClick
                    )
                }
            )
        }
    ) { innerPadding ->
        if (uiState.isLoading) {
            AppFullScreenProgressIndicator(
                modifier = Modifier
                    .padding(innerPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.default_padding_large)
                )
            ) {
                items(uiState.products) { product ->
                    ProductItem(
                        modifier = Modifier,
                        title = product.productTitle,
                        price = "${stringResource(R.string.rupee_symbol)} ${product.productPrice}",
                        image = product.productImages.first()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductsScreenPreview(
    @PreviewParameter(ProductsPreviewParameter::class) products: List<Product>
) {
    AppPreviewTheme {
        ProductsScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = ProductsUiState(
                products = products
            ),
            onFilterClick = {}
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductsScreenLoadingPreview() {
    AppPreviewTheme {
        ProductsScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = ProductsUiState(
                isLoading = true
            ),
            onFilterClick = {}
        )
    }
}
