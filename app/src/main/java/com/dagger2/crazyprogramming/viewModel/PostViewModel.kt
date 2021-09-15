package com.dagger2.crazyprogramming.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dagger2.crazyprogramming.network.ApiEmptyResponse
import com.dagger2.crazyprogramming.network.ApiErrorResponse
import com.dagger2.crazyprogramming.network.ApiResponse
import com.dagger2.crazyprogramming.network.ApiSuccessResponse
import com.dagger2.crazyprogramming.repository.PostRepository
import com.dagger2.crazyprogramming.utils.BaseViewModel
import com.hilt.crazyprogramming.model.postById.PostById
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(application: Application, private val postRepository: PostRepository): BaseViewModel(application) {

    fun getViewModelPostById(postId: Int): LiveData<PostById> {
        val postIdValue = MutableLiveData<PostById>()

        if (checkNetworkStatus()) {
            apiCallStatus.postValue("LOADING")

            viewModelScope.launch {
                when (val apiResponse = ApiResponse.create(postRepository.getPostByIdRepo(postId))) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue("SUCCESS")
                        postIdValue.postValue(apiResponse.body)
                        Log.d("response", apiResponse.toString())
                    }
                    is ApiErrorResponse -> {
                        apiCallStatus.postValue("ERROR")
                        Log.d("response", apiResponse.toString())
                    }
                    is ApiEmptyResponse -> {
                        apiCallStatus.postValue("EMPTY")
                        Log.d("response", apiResponse.toString())
                    }
                }
            }
        }
        Log.d("PostByIdViewModel", postId.toString())
        return postIdValue
    }
}