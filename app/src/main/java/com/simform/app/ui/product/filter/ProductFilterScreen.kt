package com.simform.app.ui.product.filter

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.simform.app.R
import com.simform.app.ui.components.ScreenTitle
import com.simform.design.button.AppButton
import com.simform.design.scaffold.AppScaffold
import com.simform.design.text.AppText
import com.simform.design.textfield.AppOutlinedTextField
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

/**
 * The product filter route to show filter screen.
 *
 * @param modifier The [Modifier]
 * @param viewModel The [ProductFilterViewModel]
 */
@Composable
fun ProductFilterRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductFilterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductFilterScreen(
        modifier = modifier,
        uiState = uiState,
        onPriceMinChange = viewModel::onPriceMinChange,
        onPriceMaxChange = viewModel::onPriceMaxChange,
        onFilterClick = viewModel::onFilterClick,
        onResetClick = viewModel::onResetClick
    )
}

/**
 * Standalone screen to show filter screen.
 *
 * @param modifier The [Modifier]
 * @param uiState The [ProductFilterUiState]
 * @param onPriceMinChange The callback to handle price min change
 * @param onPriceMaxChange The callback to handle price max change
 * @param onFilterClick The callback to handle continue click
 * @param onResetClick The callback to handle reset click
 */
@Composable
private fun ProductFilterScreen(
    modifier: Modifier = Modifier,
    uiState: ProductFilterUiState,
    onPriceMinChange: (Int?) -> Unit,
    onPriceMaxChange: (Int?) -> Unit,
    onFilterClick: () -> Unit,
    onResetClick: () -> Unit
) {
    AppScaffold(
        modifier = modifier,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxHeight()
                .padding(dimensionResource(R.dimen.appSidePadding))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.default_vertical_padding),
                alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.screen_title_spacing)))
            ScreenTitle(title = stringResource(R.string.title_filter_product))
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.screen_title_spacing)))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(
                    space = dimensionResource(R.dimen.default_vertical_padding),
                    alignment = Alignment.CenterVertically
                ),
            ) {
                FilterField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.title_enter_price_min),
                    value = uiState.priceMin,
                    onValueChange = onPriceMinChange
                )
                FilterField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = stringResource(R.string.title_enter_price_max),
                    value = uiState.priceMax,
                    onValueChange = onPriceMaxChange
                )
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.default_btn_padding)),
                    onClick = onFilterClick
                ) {
                    AppText(
                        text = stringResource(R.string.title_filter),
                        textColor = AppTheme.appColorScheme.white
                    )
                }
                AppButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = dimensionResource(R.dimen.default_btn_padding)),
                    onClick = onResetClick
                ) {
                    AppText(
                        text = stringResource(R.string.title_reset),
                        textColor = AppTheme.appColorScheme.white
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: Int?,
    onValueChange: (Int?) -> Unit
) {
    AppOutlinedTextField(
        modifier = modifier,
        value = value?.toString() ?: "",
        placeholder = {
            AppText(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = {
            onValueChange(it.toIntOrNull())
        }
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductFilterScreenPreview() {
    AppPreviewTheme {
        ProductFilterScreen(
            modifier = Modifier
                .fillMaxSize(),
            uiState = ProductFilterUiState(),
            onPriceMinChange = {},
            onPriceMaxChange = {},
            onFilterClick = {},
            onResetClick = {}
        )
    }
}
