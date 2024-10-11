package com.simform.app.ui.product.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.simform.app.R
import com.simform.design.image.AppImage
import com.simform.design.text.AppText
import com.simform.design.theme.AppPreviewTheme
import com.simform.design.theme.AppTheme

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    productImageHeight: Dp = dimensionResource(R.dimen.product_item_image_height),
    title: String,
    price: String,
    image: String,
) {
    Column(
        modifier = modifier,
    ) {
        AppImage(
            modifier = Modifier
                .height(productImageHeight),
            url = image,
            placeholder = painterResource(com.simform.design.R.drawable.ic_image_placeholder)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.appColorScheme.secondary)
                .padding(dimensionResource(R.dimen.default_padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppText(
                modifier = Modifier
                    .weight(1F),
                text = title,
                style = AppTheme.appTypography.body1Bold,
                maxLines = 1
            )
            AppText(
                text = price
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProductItemPreview() {
    AppPreviewTheme {
        ProductItem(
            modifier = Modifier,
            title = "Product Title",
            price = "${stringResource(R.string.rupee_symbol)} 300",
            image = ""
        )
    }
}
