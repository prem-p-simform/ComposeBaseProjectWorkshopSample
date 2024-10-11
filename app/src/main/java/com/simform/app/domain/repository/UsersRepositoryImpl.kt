package com.simform.app.domain.repository

import com.simform.app.common.dispatcher.IoDispatcher
import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.UserResponse
import com.simform.app.domain.remote.service.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userService: UserService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UsersRepository {
    override suspend fun loadUsers(page: Int): ApiResult<UserResponse> =
        withContext(ioDispatcher) {
            userService.loadUsers(page)
        }
}