package com.dagger2.crazyprogramming.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dagger2.crazyprogramming.model.user.User
import com.dagger2.crazyprogramming.network.ApiEmptyResponse
import com.dagger2.crazyprogramming.network.ApiErrorResponse
import com.dagger2.crazyprogramming.network.ApiResponse
import com.dagger2.crazyprogramming.network.ApiSuccessResponse
import com.dagger2.crazyprogramming.repository.DiRepository
import com.dagger2.crazyprogramming.utils.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiViewModel @Inject constructor(application: Application, private val diRepository: DiRepository): BaseViewModel(application) {
    fun getViewModelUser(): LiveData<List<User>> {
        val user = MutableLiveData<List<User>>()

        if (checkNetworkStatus()) {
            apiCallStatus.postValue("LOADING")
            viewModelScope.launch {
                when (val apiResponse = ApiResponse.create(diRepository.getUserRepo())) {
                    is ApiSuccessResponse -> {
                        apiCallStatus.postValue("SUCCESS")
                        user.postValue(apiResponse.body)
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
        Log.d("response1", user.toString())
        return user
    }
}