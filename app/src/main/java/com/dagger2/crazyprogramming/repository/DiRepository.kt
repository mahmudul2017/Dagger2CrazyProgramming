package com.dagger2.crazyprogramming.repository

import com.dagger2.crazyprogramming.dInjector.ApiServiceImpl
import com.dagger2.crazyprogramming.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DiRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {
    suspend fun getUserRepo(): Response<List<User>> {
        return withContext(Dispatchers.IO) {
            apiServiceImpl.getUserImpl()
        }
    }
}