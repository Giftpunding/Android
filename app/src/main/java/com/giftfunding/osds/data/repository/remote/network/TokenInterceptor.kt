package com.giftfunding.osds.data.repository.remote.network

import android.util.Log
import com.giftfunding.osds.data.repository.local.pref.LoginSharedPreference
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor(
    private val loginSharedPreference: LoginSharedPreference
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()

        val jwt = loginSharedPreference.accessToken
        if (jwt!!.isNotEmpty()) {
            Log.d("TokenInterceptor", jwt)
            requestBuilder.header("Authorization", "Bearer $jwt")
        } else {
            Log.d("TokenInterceptor", "Token is empty")
        }

        return chain.proceed(requestBuilder.build())
    }
}