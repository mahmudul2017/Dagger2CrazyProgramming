package com.dagger2.crazyprogramming.utils

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dagger2.crazyprogramming.R

open class BaseViewModel(private val context: Application) : ViewModel() {
    val apiCallStatus: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun checkNetworkStatus(application: Application) =
        if (isNetworkAvailable(application)) {
            true
        } else {
            showErrorToast(application, application.getString(R.string.net_error_msg))
            false
        }

    fun checkNetworkStatus() = if (isNetworkAvailable(context)) {
        true
    } else {
        showErrorToast(context, context.getString(R.string.net_error_msg))
        false
    }

    /* fun onAppExit(preferences: SharedPreferences) {
        preferences.edit().apply {
            putString("LoggedUser", "")
            apply()
        }
    } */
}