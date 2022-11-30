package com.giftfunding.osds.data.repository.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule(
    private val tokenInterceptor: TokenInterceptor
) {

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .build()

    private val provideOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .addInterceptor(tokenInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .client(provideOkHttpClient)
        .baseUrl("http://dev.taxijjang.site")
        .build()
}
