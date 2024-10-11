package com.simform.app.domain.remote.service

import com.simform.app.domain.remote.apiresult.ApiResult
import com.simform.app.domain.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("https://randomuser.me/api/?inc=name,location,picture,login&results=50&seed=abc")
    suspend fun loadUsers(@Query("page") page: Int): ApiResult<UserResponse>
}
