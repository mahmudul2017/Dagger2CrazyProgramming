package com.dagger2.crazyprogramming.repository

import com.dagger2.crazyprogramming.dInjector.ApiServiceImpl
import com.hilt.crazyprogramming.model.postById.PostById
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl) {
    suspend fun getPostByIdRepo(postId: Int): Response<PostById> {
        return withContext(Dispatchers.IO) {
            apiServiceImpl.getPostByIdImpl(postId)
        }
    }
}