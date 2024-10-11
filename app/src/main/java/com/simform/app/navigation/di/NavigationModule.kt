package com.simform.app.navigation.di

import android.content.Context
import com.simform.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun provideNavigator(@ApplicationContext context: Context): Navigator =
        Navigator.getInstance(context)
}