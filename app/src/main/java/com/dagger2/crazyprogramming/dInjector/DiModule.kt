package com.dagger2.crazyprogramming.dInjector

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dagger2.crazyprogramming.BuildConfig
import com.dagger2.crazyprogramming.network.ApiService
import com.dagger2.crazyprogramming.utils.SHARED_PREFS_KEY
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object DiModule {
    @Singleton
    @Provides
    fun provideApiService(preferences: SharedPreferences): ApiService {
    val levelType: HttpLoggingInterceptor.Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
        HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    val logging = HttpLoggingInterceptor()
    logging.setLevel(levelType)

    val clientBuilder = OkHttpClient().newBuilder()
        .connectTimeout(7, TimeUnit.SECONDS)
        .callTimeout(7, TimeUnit.SECONDS)
        .addInterceptor(logging)

    val client = clientBuilder.build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}

   @Singleton
   @Provides
   @JvmStatic
   fun provideSharedPreferences(app: Application): SharedPreferences {
      return app.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
   }
}