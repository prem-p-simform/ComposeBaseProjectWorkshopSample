package com.simform.app.domain.repository.product

import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.ProductsResponse

interface ProductRepository {

    suspend fun getProducts(): ApiResult<ProductsResponse>

    suspend fun getProducts(
        priceMin: Int? = null,
        priceMax: Int? = null
    ): ApiResult<ProductsResponse>
}