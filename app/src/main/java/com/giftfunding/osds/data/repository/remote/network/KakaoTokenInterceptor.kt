package com.giftfunding.osds.data.repository.remote.network

import android.util.Log
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class KakaoTokenInterceptor(
    private val apiKey: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        requestBuilder.header("Authorization", "KakaoAK $apiKey")
        return chain.proceed(requestBuilder.build())
    }
}