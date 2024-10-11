package com.simform.app.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simform.app.common.dispatcher.IoDispatcher
import com.simform.app.domain.model.User
import com.simform.app.domain.remote.apiresult.mapOnSuccess
import com.simform.app.domain.remote.apiresult.onError
import com.simform.app.domain.remote.apiresult.onException
import com.simform.app.domain.remote.apiresult.onSuccess
import com.simform.app.domain.repository.UsersRepository
import com.simform.app.ui.user.navigation.UserDetails
import com.simform.navigation.Navigator
import com.simform.navigation.getResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * The ViewModel for [UsersScreen]
 * 
 * @param usersRepository The [UsersRepository] instance
 * @param ioDispatcher The IO coroutine dispatcher
 * @param navigator The [Navigator] instance
 */
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val navigator: Navigator
) : ViewModel() {

    private val _uiState = MutableStateFlow(getDefaultUiState())
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = getDefaultUiState()
    )

    init {
        viewModelScope.launch(ioDispatcher) {
            _uiState.update { it.copy(isLoading = true) }
            usersRepository.loadUsers(1)
                .mapOnSuccess { it.results }
                .onSuccess { users ->
                    _uiState.update { it.copy(users = users) }
                }
                .onError { code, message -> Timber.e(message) }
                .onException { Timber.e(it) }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    /**
     * Called when a user is clicked.
     * 
     * @param user The clicked [User]
     */
    fun onUserClick(user: User) {
        navigator.getResult<UserDetails.Result>(
            destinationKey = UserDetails.Result.KEY,
            viewModel = this@UsersViewModel
        ) {
            if (this == null) return@getResult
            _uiState.update { state ->
                state.copy(
                    users = state.users.map {
                        if (it == user) it.copy(name = User.Name(first = name)) else it
                    }
                )
            }
        }
        navigator.navigate(UserDetails(user = user))
    }

    private fun getDefaultUiState(): UsersUiState =
        UsersUiState()
}
