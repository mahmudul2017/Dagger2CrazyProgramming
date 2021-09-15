package com.dagger2.crazyprogramming.dInjector

import com.dagger2.crazyprogramming.model.user.User
import com.dagger2.crazyprogramming.network.ApiService
import com.hilt.crazyprogramming.model.postById.PostById
import retrofit2.Response
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getUserImpl(): Response<List<User>> = apiService.getUser()

    suspend fun getPostByIdImpl(postId: Int): Response<PostById> = apiService.getPostById(postId)
}