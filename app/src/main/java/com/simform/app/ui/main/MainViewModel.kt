package com.simform.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.simform.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [MainScreen]
 *
 * @property navigator The instance of Navigator
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val navigator: Navigator
) : ViewModel()