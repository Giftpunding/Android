package com.giftfunding.osds.data.repository.remote.service

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("/api/user/login/token")
    fun postLoginToken(
        @Query("kakaoToken") kakaoToken: String
    ): Call<LoginResponse>

}