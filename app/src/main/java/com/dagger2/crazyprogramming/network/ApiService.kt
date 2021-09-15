package com.dagger2.crazyprogramming.network

import com.dagger2.crazyprogramming.model.user.User
import com.hilt.crazyprogramming.model.postById.PostById
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    suspend fun getUser() : Response<List<User>>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int) : Response<PostById>
}