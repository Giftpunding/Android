package com.giftfunding.osds.data.repository.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule(
    tokenInterceptor: TokenInterceptor,
    kakaoTokenInterceptor: KakaoTokenInterceptor
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

    private val provideKakaoInterceptor = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addNetworkInterceptor(HttpLoggingInterceptor())
        .addInterceptor(kakaoTokenInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .client(provideOkHttpClient)
        .baseUrl(BASE_URL)
        .build()

    val kakaoRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .client(provideKakaoInterceptor)
        .baseUrl(KAKAO_BASE_URL)
        .build()

    companion object {
        private val BASE_URL = "http://dev.taxijjang.site"
        private val KAKAO_BASE_URL = "https://dapi.kakao.com"
    }
}
