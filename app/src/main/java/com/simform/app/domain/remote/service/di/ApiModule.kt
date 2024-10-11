package com.simform.app.domain.remote.service.di

import android.content.Context
import com.arkanakeys.ArkanaKeys
import com.simform.bagel.intercept.BagelInterceptor
import com.simform.app.BuildConfig
import com.simform.app.domain.remote.apiresult.ApiResultCallAdapterFactory
import com.simform.app.domain.remote.service.ApiService
import com.simform.app.domain.remote.service.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val HTTP_LOGGING_INTERCEPTOR: String = "HTTP_LOGGING_INTERCEPTOR"
private const val OKHTTP_CLIENT = "OKHTTP_CLIENT"
private const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10 MB
private const val NETWORK_CALL_TIMEOUT: Long = 1 // Minute

/**
 * Provides remote APIs dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideBagelInterceptor(): BagelInterceptor = BagelInterceptor.getInstance()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = false
        prettyPrint = true
        encodeDefaults = true
    }

    @Singleton
    @Provides
    @Named(HTTP_LOGGING_INTERCEPTOR)
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @Named(OKHTTP_CLIENT)
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        @Named(HTTP_LOGGING_INTERCEPTOR) httpLoggingInterceptor: HttpLoggingInterceptor,
        bagelInterceptor: BagelInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(bagelInterceptor)
        .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
        .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.MINUTES)
        .build()

    @Provides
    fun provideRetrofit(
        @Named(OKHTTP_CLIENT) okHttpClient: OkHttpClient,
        apiResultCallAdapterFactory: ApiResultCallAdapterFactory,
        networkJson: Json,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ArkanaKeys.Global.productsEndPoint)
        .client(okHttpClient)
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(apiResultCallAdapterFactory)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create()

    @Provides
    fun provideProductService(apiService: ApiService): ProductService = apiService
}