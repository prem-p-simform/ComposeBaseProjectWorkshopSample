package com.simform.app.domain.remote.service

import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    private companion object {
        const val PRODUCTS = "products"
    }

    @GET(PRODUCTS)
    suspend fun getProducts(
        @Query("limit") limit: Int = 200,
        @Query("offset") offset: Int = 0,
        @Query("price_min") priceMin: Int? = null,
        @Query("price_max") priceMax: Int? = null,
    ): ApiResult<ProductsResponse>
}
