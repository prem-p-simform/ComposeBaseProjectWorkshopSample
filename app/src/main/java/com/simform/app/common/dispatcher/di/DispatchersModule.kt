package com.simform.app.common.dispatcher.di

import com.simform.app.common.dispatcher.DefaultDispatcher
import com.simform.app.common.dispatcher.IoDispatcher
import com.simform.app.common.dispatcher.MainDispatcher
import com.simform.app.common.dispatcher.MainImmediateDispatcher
import com.simform.app.common.dispatcher.UnconfinedDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @MainImmediateDispatcher
    fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @Provides
    @UnconfinedDispatcher
    fun provideUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined

    @Provides
    @IoDispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
