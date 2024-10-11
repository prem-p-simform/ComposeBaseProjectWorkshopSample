package com.simform.app.domain.repository.product

import com.simform.app.common.dispatcher.IoDispatcher
import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.ProductsResponse
import com.simform.app.domain.remote.service.ProductService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {
    override suspend fun getProducts(): ApiResult<ProductsResponse> = withContext(ioDispatcher) {
        productService.getProducts()
    }

    override suspend fun getProducts(
        priceMin: Int?,
        priceMax: Int?
    ): ApiResult<ProductsResponse> = withContext(ioDispatcher) {
        productService.getProducts(
            priceMin = priceMin,
            priceMax = priceMax
        )
    }
}