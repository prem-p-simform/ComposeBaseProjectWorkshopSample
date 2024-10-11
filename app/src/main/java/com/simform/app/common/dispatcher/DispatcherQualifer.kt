package com.simform.app.common.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class MainDispatcher

@Qualifier
@Retention
annotation class MainImmediateDispatcher

@Qualifier
@Retention
annotation class DefaultDispatcher

@Qualifier
@Retention
annotation class IoDispatcher

@Qualifier
@Retention
annotation class UnconfinedDispatcher
