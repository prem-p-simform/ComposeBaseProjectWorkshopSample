package com.simform.app.ui.user.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.app.ui.user.navigation.UserDetails
import com.simform.navigation.Navigator
import com.simform.navigation.core.getNavArgs
import com.simform.navigation.navigateUpWithResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * The ViewModel for [UserDetailScreen]
 * 
 * @param savedStateHandle The [SavedStateHandle] to retrieve data
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator
) : ViewModel() {

    private val navArgs: UserDetails = requireNotNull(savedStateHandle.getNavArgs<UserDetails>())

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    /**
     * Called when there is a change in [name]
     * 
     * @param name The updated name
     */
    fun onNameChange(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    /**
     * Called when users presses back.
     */
    fun onBackClick() {
        navigator.navigateUpWithResult(
            key = UserDetails.Result.KEY,
            result = UserDetails.Result(name = uiState.value.name)
        )
    }

    private fun getDefaultUiState(): UserDetailUiState = UserDetailUiState(
        user = navArgs.user
    )
}
