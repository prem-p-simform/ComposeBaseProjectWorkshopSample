package com.simform.app.domain.repository

import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.UserResponse
import retrofit2.http.Query

interface UsersRepository {
    suspend fun loadUsers(@Query("page") page: Int): ApiResult<UserResponse>
}
