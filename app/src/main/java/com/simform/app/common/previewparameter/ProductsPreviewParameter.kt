package com.simform.app.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.simform.app.domain.model.Category
import com.simform.app.domain.model.Product

class ProductsPreviewParameter : PreviewParameterProvider<List<Product>> {
    override val values: Sequence<List<Product>> = sequenceOf(
        listOf(
            Product(
                productId = 1,
                productTitle = "Stylish Sunglasses",
                productPrice = 2999, // Price in cents or similar units
                productDescription = "UV protection and polarized lenses for a clear view.",
                productCategory = Category(
                    categoryId = 1,
                    categoryName = "Accessories",
                    categoryImage = "https://placeimg.com/640/480/any?r=0.1"
                ),
                productImages = listOf(
                    "https://placeimg.com/640/480/any?r=0.2",
                    "https://placeimg.com/640/480/any?r=0.3"
                )
            ),
            Product(
                productId = 2,
                productTitle = "Comfortable Backpack",
                productPrice = 4999, // Price in cents or similar units
                productDescription = "Spacious and durable backpack for everyday use.",
                productCategory = Category(
                    categoryId = 2,
                    categoryName = "Bags",
                    categoryImage = "https://placeimg.com/640/480/any?r=0.4"
                ),
                productImages = listOf(
                    "https://placeimg.com/640/480/any?r=0.5",
                    "https://placeimg.com/640/480/any?r=0.6"
                )
            ),
            Product(
                productId = 3,
                productTitle = "Wireless Headphones",
                productPrice = 7999, // Price in cents or similar units
                productDescription = "High-quality sound and noise cancellation for immersive audio.",
                productCategory = Category(
                    categoryId = 3,
                    categoryName = "Electronics",
                    categoryImage = "https://placeimg.com/640/480/any?r=0.7"
                ),
                productImages = listOf(
                    "https://placeimg.com/640/480/any?r=0.8",
                    "https://placeimg.com/640/480/any?r=0.9"
                )
            )
        )
    )
}